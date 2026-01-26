#include <jni.h>
#include <android/bitmap.h>
#include <android/log.h>
#include <string>
#include <vector>
#include <memory>
#include <cstring>
#include <cmath>
#include <mutex>
#include <chrono>
#include <climits>
#include <unordered_map>
#include <fstream>
#include <atomic>
#include <algorithm>
#include <functional>
#include <filesystem>
#include <thread>
#include <list>
#include <deque>
#include <condition_variable>

#include "rlottie.h"
#include "webp/decode.h"
#include "webp/demux.h"
#include "libyuv.h"

extern "C" {
#include <libavformat/avformat.h>
#include <libavcodec/avcodec.h>
#include <libswscale/swscale.h>
#include <libavutil/imgutils.h>
#include <libavutil/mem.h>
#include <libavutil/pixdesc.h>
}

#include <vpx/vpx_codec.h>
#include <vpx/vpx_decoder.h>
#include <vpx/vp8dx.h>

#define LOG_TAG "StickerRenderJNI"
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)


class ScratchBufferPool {
private:
    std::mutex mtx;
    std::vector<uint8_t> buffer;
public:
    uint8_t* get(size_t size) {
        std::lock_guard<std::mutex> lock(mtx);
        if (buffer.size() < size) buffer.resize(size);
        return buffer.data();
    }
};
static ScratchBufferPool gScratchPool;


static thread_local std::vector<uint8_t> g_thread_scratch;
uint8_t* get_thread_scratch(size_t size) {
    if (g_thread_scratch.size() < size) {
        g_thread_scratch.resize(size);
    }
    return g_thread_scratch.data();
}


static uint64_t fnv1a_hash64(const uint8_t* data, size_t size) {
    const uint64_t FNV_OFFSET = 14695981039346656037ULL;
    const uint64_t FNV_PRIME = 1099511628211ULL;
    uint64_t hash = FNV_OFFSET;
    for (size_t i = 0; i < size; ++i) {
        hash ^= static_cast<uint64_t>(data[i]);
        hash *= FNV_PRIME;
    }
    return hash;
}

static std::string u64_to_hex(uint64_t v) {
    char buf[33];
    snprintf(buf, sizeof(buf), "%016zx", static_cast<size_t>(v));
    return std::string(buf);
}

struct DiskWriteTask {
    std::string path;
    std::vector<uint8_t> payload;
};

static std::mutex gDiskQueueMutex;
static std::condition_variable gDiskQueueCv;
static std::deque<DiskWriteTask> gDiskQueue;
static std::atomic<bool> gDiskWorkerRunning{false};
static std::thread gDiskWorker;

static void diskWorkerThread() {
    std::unique_lock<std::mutex> lk(gDiskQueueMutex);
    while (true) {
        gDiskQueueCv.wait(lk, []{ return !gDiskQueue.empty() || !gDiskWorkerRunning.load(); });
        if (gDiskQueue.empty() && !gDiskWorkerRunning.load()) {
            break;
        }

        if (gDiskQueue.empty()) continue;

        DiskWriteTask task = std::move(gDiskQueue.front());
        gDiskQueue.pop_front();
        lk.unlock();

        std::string tmpPath = task.path + ".tmp";
        {
            std::ofstream ofs(tmpPath, std::ios::binary | std::ios::trunc);
            if (ofs) {
                ofs.write(reinterpret_cast<const char*>(task.payload.data()), task.payload.size());
                ofs.flush();
            }
        }
        if (std::filesystem::exists(tmpPath) && std::filesystem::file_size(tmpPath) > 0) {
            std::error_code ec;
            std::filesystem::rename(tmpPath, task.path, ec);
        }

        lk.lock();
    }
}

static void ensureDiskWorker() {
    bool expected = false;
    if (gDiskWorkerRunning.compare_exchange_strong(expected, true)) {
        gDiskWorker = std::thread(diskWorkerThread);
    }
}

static void stopDiskWorker() {
    bool expected = true;
    if (gDiskWorkerRunning.compare_exchange_strong(expected, false)) {
        gDiskQueueCv.notify_all();
        if (gDiskWorker.joinable()) gDiskWorker.join();
    }
}

static void enqueueDiskWriteTask(const std::string& path, std::vector<uint8_t>&& payload) {
    ensureDiskWorker();
    {
        std::lock_guard<std::mutex> lk(gDiskQueueMutex);
        gDiskQueue.push_back(DiskWriteTask{path, std::move(payload)});
    }
    gDiskQueueCv.notify_one();
}

static std::mutex gCacheMutex;
struct CacheEntry {
    std::string key;
    std::shared_ptr<std::vector<uint8_t>> data;
    size_t w;
    size_t h;
};

static std::list<CacheEntry> gCacheList;
static std::unordered_map<std::string, std::list<CacheEntry>::iterator> gCacheMap;

static size_t gCacheSizeBytes = 0;
static size_t gCacheMaxBytes = 300ULL * 1024ULL * 1024ULL;
static std::string gCacheDir;

static void ensureCacheDirExists() {
    if (gCacheDir.empty()) return;
    try {
        std::filesystem::create_directories(gCacheDir);
    } catch (...) {}
}

static void evictIfNeeded() {
    while (gCacheSizeBytes > gCacheMaxBytes && !gCacheList.empty()) {
        auto& last = gCacheList.back();
        gCacheMap.erase(last.key);
        if (last.data) {
            gCacheSizeBytes -= last.data->size();
        }
        gCacheList.pop_back();
    }
}

static std::string cacheFilePathForKey(const std::string& key) {
    if (gCacheDir.empty()) return "";

    uint64_t h64 = fnv1a_hash64(reinterpret_cast<const uint8_t*>(key.data()), key.size());

    char fname[128];
    snprintf(fname, sizeof(fname), "v2_%016llx.bin", (unsigned long long)h64);

    std::filesystem::path p(gCacheDir);
    p /= fname;
    return p.string();
}

static bool loadCacheFromDisk(const std::string& key, std::shared_ptr<std::vector<uint8_t>>& outData, int& outW, int& outH) {
    std::string path = cacheFilePathForKey(key);
    if (path.empty()) return false;
    try {
        std::ifstream ifs(path, std::ios::binary);
        if (!ifs) return false;

        ifs.seekg(0, std::ios::end);
        std::streamsize fsize = ifs.tellg();
        ifs.seekg(0, std::ios::beg);

        if (fsize < sizeof(int) * 2) return false;

        int w = 0, h = 0;
        ifs.read(reinterpret_cast<char*>(&w), sizeof(int));
        ifs.read(reinterpret_cast<char*>(&h), sizeof(int));

        if (w <= 0 || h <= 0) return false;

        size_t expectedBytes = static_cast<size_t>(w) * h * 4;
        if (static_cast<size_t>(fsize) < sizeof(int) * 2 + expectedBytes) return false;

        auto buf = std::make_shared<std::vector<uint8_t>>(expectedBytes);
        ifs.read(reinterpret_cast<char*>(buf->data()), expectedBytes);

        if (!ifs) return false;

        outData = buf;
        outW = w;
        outH = h;
        return true;
    } catch (...) {
        return false;
    }
}

static bool retrieveFromCache(const std::string& key, uint8_t* dst, int dstStride, int w, int h) {
    std::unique_lock<std::mutex> lk(gCacheMutex);
    auto it = gCacheMap.find(key);
    if (it != gCacheMap.end()) {
        gCacheList.splice(gCacheList.begin(), gCacheList, it->second);
        auto& ce = *it->second;
        if (ce.w == w && ce.h == h && ce.data) {
            libyuv::CopyPlane(ce.data->data(), w * 4, dst, dstStride, w * 4, h);
            return true;
        }
    }

    lk.unlock();

    std::shared_ptr<std::vector<uint8_t>> diskBuf;
    int dw = 0, dh = 0;
    bool loaded = loadCacheFromDisk(key, diskBuf, dw, dh);

    if (loaded && diskBuf) {
        lk.lock();

        if (gCacheMap.find(key) == gCacheMap.end() && dw == w && dh == h) {
            CacheEntry newEntry{key, diskBuf, (size_t)dw, (size_t)dh};
            gCacheList.push_front(newEntry);
            gCacheMap[key] = gCacheList.begin();
            gCacheSizeBytes += diskBuf->size();
            evictIfNeeded();
        }

        const auto& buf = *diskBuf;
        if (dstStride == w * 4) {
            memcpy(dst, buf.data(), (size_t)w * h * 4);
        } else {
            for (int y = 0; y < h; ++y) {
                memcpy(dst + (size_t)y * dstStride,
                       buf.data() + (size_t)y * w * 4,
                       (size_t)w * 4);
            }
        }
        return true;
    }
    return false;
}

static std::string makeFastKey(const std::string& sourceHash, int frame, int w, int h) {
    char buf[128];
    snprintf(buf, sizeof(buf), "%s_%d_%dx%d", sourceHash.c_str(), frame, w, h);
    return std::string(buf);
}

static const size_t MAX_LOTTIE_FRAMES_PER_INSTANCE = 40;
static std::unordered_map<std::string, int> gFramesCachedPerSource;
static std::mutex gFramesMutex;

static void storeToCache(const std::string& key, const uint8_t* src, int srcStride, int w, int h, const std::string& filePath) {
    size_t bytes = static_cast<size_t>(w) * h * 4;
    auto buf = std::make_shared<std::vector<uint8_t>>(bytes);

    libyuv::CopyPlane(src, srcStride, buf->data(), w * 4, w * 4, h);

    {
        std::lock_guard<std::mutex> lk(gCacheMutex);
        if (gCacheMap.find(key) == gCacheMap.end()) {
            CacheEntry newEntry{key, buf, static_cast<size_t>(w), static_cast<size_t>(h)};
            gCacheList.push_front(newEntry);
            gCacheMap[key] = gCacheList.begin();
            gCacheSizeBytes += buf->size();
            evictIfNeeded();
        }
    }

    std::string srcId;
    {
        auto pos = key.find(':');
        if (pos != std::string::npos) {
            auto pos2 = key.find(':', pos+1);
            if (pos2 != std::string::npos) srcId = key.substr(pos+1, pos2 - (pos+1));
        }
    }
    bool skipDisk = false;
    if (!srcId.empty()) {
        std::lock_guard<std::mutex> lk(gFramesMutex);
        int &count = gFramesCachedPerSource[srcId];
        if (count >= (int)MAX_LOTTIE_FRAMES_PER_INSTANCE) skipDisk = true;
        else ++count;
    }
    if (!skipDisk && !filePath.empty()) {
        std::vector<uint8_t> payload;
        payload.reserve(sizeof(int)*2 + bytes);
        int wi = w, hi = h;
        payload.insert(payload.end(), reinterpret_cast<uint8_t*>(&wi), reinterpret_cast<uint8_t*>(&wi) + sizeof(int));
        payload.insert(payload.end(), reinterpret_cast<uint8_t*>(&hi), reinterpret_cast<uint8_t*>(&hi) + sizeof(int));
        payload.insert(payload.end(), buf->begin(), buf->end());
        enqueueDiskWriteTask(filePath, std::move(payload));
    }
}

extern "C" JNIEXPORT void JNICALL
Java_com_xxcactussell_jni_NativeStickerCore_setCacheDir(JNIEnv* env, jobject, jstring path) {
    const char* p = env->GetStringUTFChars(path, nullptr);
    gCacheDir = std::string(p);
    env->ReleaseStringUTFChars(path, p);
    ensureCacheDirExists();
}

extern "C" JNIEXPORT void JNICALL
Java_com_xxcactussell_jni_NativeStickerCore_shutdownCache(JNIEnv*, jobject) {
    stopDiskWorker();
}

class BitmapLocker {
public:
    BitmapLocker(JNIEnv* env, jobject bitmap) : env_(env), bitmap_(bitmap) {
        if (AndroidBitmap_lockPixels(env_, bitmap_, &pixels_) < 0) {
            pixels_ = nullptr;
        }
        AndroidBitmap_getInfo(env_, bitmap_, &info_);
    }
    ~BitmapLocker() {
        if (pixels_) {
            AndroidBitmap_unlockPixels(env_, bitmap_);
        }
    }
    void* getPixels() const { return pixels_; }
    const AndroidBitmapInfo& getInfo() const { return info_; }
    bool isValid() const { return pixels_ != nullptr; }
private:
    JNIEnv* env_;
    jobject bitmap_;
    void* pixels_ = nullptr;
    AndroidBitmapInfo info_{};
};

class LottieInstance {
public:
    std::unique_ptr<rlottie::Animation> animation{};
    std::unique_ptr<uint32_t> renderBuffer;
    std::string sourceHash;
    int width = 0;
    int height = 0;
    int renderWidth = 0;
    int renderHeight = 0;
    long long instanceId = 0;
    explicit LottieInstance(const std::string& json) {
        static std::atomic<long long> s_counter{1};
        instanceId = s_counter.fetch_add(1, std::memory_order_relaxed);
        uint64_t h64 = 0;
        if (!json.empty()) h64 = fnv1a_hash64(reinterpret_cast<const uint8_t*>(json.data()), json.size());
        sourceHash = u64_to_hex(h64);
        animation = rlottie::Animation::loadFromData(json, json);
        if (animation) {
            size_t w, h;
            animation->size(w, h);
            width = static_cast<int>(w);
            height = static_cast<int>(h);
        }
    }
    void prepareRendering(int w, int h) {
        renderWidth = w;
        renderHeight = h;
        if (renderWidth > 0 && renderHeight > 0) {
            size_t needed = static_cast<size_t>(renderWidth) * renderHeight;
            if (!renderBuffer || renderBuffer.get() == nullptr) {
                renderBuffer = std::unique_ptr<uint32_t>(new uint32_t[needed]);
            }
        }
    }
    void renderToBuffer(int frame) const {
        if (!animation || !renderBuffer) return;
        rlottie::Surface surface(renderBuffer.get(), renderWidth, renderHeight, renderWidth * 4);
        animation->renderSync(frame, surface, true);
    }
    void render(int frame, void* dstPixels, int dstStride) const {
        if (!animation || !renderBuffer) return;
        renderToBuffer(frame);
        const auto* src = reinterpret_cast<const uint8_t*>(renderBuffer.get());
        int srcStride = renderWidth * 4;
        uint8_t* dst = reinterpret_cast<uint8_t*>(dstPixels);
        if (srcStride == dstStride) {
            size_t totalSize = static_cast<size_t>(renderWidth) * renderHeight * 4;
            libyuv::ARGBToABGR(src, srcStride, dst, dstStride, renderWidth, renderHeight);
        } else {
            libyuv::ARGBToABGR(src, srcStride, dst, dstStride, renderWidth, renderHeight);
        }
    }
    int totalFrames() const { return animation ? animation->totalFrame() : 0; }
    float frameRate() const { return animation ? animation->frameRate() : 0.0f; }
};

class WebPInstance {
public:
    WebPAnimDecoder* dec = nullptr;
    WebPAnimInfo info{};
    std::vector<uint8_t> mData;
    std::string sourceHash;
    int renderWidth = 0;
    int renderHeight = 0;
    int lastTimestamp = 0;
    int frameIndex = 0;

    explicit WebPInstance(const uint8_t* data, size_t data_size) {
        mData.assign(data, data + data_size);
        uint64_t h64 = fnv1a_hash64(mData.data(), mData.size());
        sourceHash = u64_to_hex(h64);

        WebPData webp_data;
        webp_data.bytes = mData.data();
        webp_data.size = mData.size();

        WebPAnimDecoderOptions options;
        if (WebPAnimDecoderOptionsInit(&options)) {
            options.color_mode = MODE_RGBA;
            options.use_threads = 1; // 1 поток для WebP вполне достаточно для стикера
            dec = WebPAnimDecoderNew(&webp_data, &options);
            if (dec) WebPAnimDecoderGetInfo(dec, &info);
        }
    }

    ~WebPInstance() {
        if (dec) WebPAnimDecoderDelete(dec);
    }

    void prepareRendering(int w, int h) {
        renderWidth = w;
        renderHeight = h;
    }

    int renderNext(void* dstPixels, int dstStride, bool onlyAdvance);
};

int WebPInstance::renderNext(void* dstPixels, int dstStride, bool onlyAdvance) {
    if (!dec) return -1;

    uint8_t* frameRGBA = nullptr;
    int timestamp = 0;

    if (!WebPAnimDecoderGetNext(dec, &frameRGBA, &timestamp)) {
        WebPAnimDecoderReset(dec);
        lastTimestamp = 0;
        frameIndex = 0;
        if (!WebPAnimDecoderGetNext(dec, &frameRGBA, &timestamp)) return -1;
    }

    int delay = timestamp - lastTimestamp;
    if (delay <= 0) delay = 16;
    lastTimestamp = timestamp;

    if (onlyAdvance) return delay;

    int srcW = info.canvas_width;
    int srcH = info.canvas_height;
    int dstW = renderWidth > 0 ? renderWidth : srcW;
    int dstH = renderHeight > 0 ? renderHeight : srcH;
    uint8_t* dst = reinterpret_cast<uint8_t*>(dstPixels);

    if (srcW == dstW && srcH == dstH) {
        // Если размеры совпадают, сразу пишем в Bitmap с аттенюацией
        libyuv::ARGBAttenuate(frameRGBA, srcW * 4, dst, dstStride, srcW, srcH);
    } else {
        // Если нужно масштабирование:
        // 1. Берем временный буфер из пула
        uint8_t* scratch = gScratchPool.get(static_cast<size_t>(srcW) * srcH * 4);

        // 2. Делаем аттенюацию в scratch
        libyuv::ARGBAttenuate(frameRGBA, srcW * 4, scratch, srcW * 4, srcW, srcH);

        // 3. Масштабируем из scratch в целевой Bitmap
        libyuv::ARGBScale(scratch, srcW * 4, srcW, srcH,
                          dst, dstStride, dstW, dstH,
                          libyuv::kFilterLinear);
    }

    return delay;
}

#define AVIO_BUFFER_SIZE 4096

struct BufferData {
    const uint8_t *ptr;
    size_t size;
    size_t offset;
};

static int read_packet(void *opaque, uint8_t *buf, int buf_size) {
    auto *bd = (BufferData *)opaque;
    int toRead = static_cast<int>(std::min<size_t>(bd->size - bd->offset, static_cast<size_t>(buf_size)));
    if (toRead <= 0) return AVERROR_EOF;
    memcpy(buf, bd->ptr + bd->offset, toRead);
    bd->offset += toRead;
    return toRead;
}

static int64_t seek_packet(void *opaque, int64_t offset, int whence) {
    auto *bd = (BufferData *)opaque;
    int64_t new_offset = -1;
    switch (whence) {
        case SEEK_SET: new_offset = offset; break;
        case SEEK_CUR: new_offset = static_cast<int64_t>(bd->offset) + offset; break;
        case SEEK_END: new_offset = static_cast<int64_t>(bd->size) + offset; break;
        case AVSEEK_SIZE: return static_cast<int64_t>(bd->size);
        default: return -1;
    }
    if (new_offset < 0 || static_cast<size_t>(new_offset) > bd->size) return -1;
    bd->offset = static_cast<size_t>(new_offset);
    return new_offset;
}

class VpxInstance {
public:
    std::string sourceHash;
    int renderWidth = 0;
    int renderHeight = 0;
    std::once_flag initFlag;
    std::atomic<bool> initialized{false};
    int videoStreamIndex = -1;
    int frameIndex = 0;

    VpxInstance(const uint8_t* data, size_t size) {
        uint64_t h64 = fnv1a_hash64(data, size);
        sourceHash = u64_to_hex(h64);
        mBuffer.assign(data, data + size);
        buffer_data.ptr = mBuffer.data();
        buffer_data.size = mBuffer.size();
        buffer_data.offset = 0;
    }

    ~VpxInstance() { release(); }

    void prepareRendering(int w, int h) {
        renderWidth = w;
        renderHeight = h;

        if (!initialized.load()) {
            ensureInit();
        }
    }

    bool init() {
        fmt_ctx = avformat_alloc_context();
        if (!fmt_ctx) return false;

        auto* avio_buffer = static_cast<unsigned char*>(av_malloc(AVIO_BUFFER_SIZE));
        if (!avio_buffer) {
            avformat_free_context(fmt_ctx);
            return false;
        }

        avio_ctx = avio_alloc_context(avio_buffer, AVIO_BUFFER_SIZE, 0, &buffer_data, &read_packet, nullptr, &seek_packet);
        if (!avio_ctx) {
            av_free(avio_buffer);
            avformat_free_context(fmt_ctx);
            return false;
        }

        fmt_ctx->pb = avio_ctx;
        if (avformat_open_input(&fmt_ctx, nullptr, nullptr, nullptr) != 0) return false;
        if (avformat_find_stream_info(fmt_ctx, nullptr) < 0) return false;

        for (unsigned int i = 0; i < fmt_ctx->nb_streams; ++i) {
            if (fmt_ctx->streams[i]->codecpar->codec_type == AVMEDIA_TYPE_VIDEO) {
                videoStreamIndex = static_cast<int>(i);
                break;
            }
        }
        if (videoStreamIndex == -1) return false;

        AVCodecParameters* codecpar = fmt_ctx->streams[videoStreamIndex]->codecpar;

        vpx_codec_iface_t* chosen = nullptr;
        if (codecpar->codec_id == AV_CODEC_ID_VP8) {
            chosen = vpx_codec_vp8_dx();
        } else if (codecpar->codec_id == AV_CODEC_ID_VP9) {
            chosen = vpx_codec_vp9_dx();
        } else {
            return false;
        }

        vpx_codec_dec_cfg_t cfg = {};
        cfg.threads = 1;
        cfg.w = codecpar->width;
        cfg.h = codecpar->height;

        if (vpx_codec_dec_init_ver(&vpx_ctx, chosen, &cfg, 0, VPX_DECODER_ABI_VERSION) != VPX_CODEC_OK) {
            return false;
        }
        vpx_initialized = true;

        if (vpx_codec_dec_init_ver(&vpx_alpha_ctx, chosen, &cfg, 0, VPX_DECODER_ABI_VERSION) != VPX_CODEC_OK) {
            LOGE("VPX: Failed to init alpha decoder");
        } else {
            vpx_alpha_initialized = true;
        }

        if (codecpar->width > 0 && codecpar->height > 0) {
            videoWidth = codecpar->width;
            videoHeight = codecpar->height;
        }

        packet = av_packet_alloc();
        initialized.store(true, std::memory_order_release);
        return true;
    }

    bool ensureInit() {
        std::call_once(initFlag, [this] { init(); });
        return initialized.load(std::memory_order_acquire);
    }

    int renderNextFrameAndGetDelay(uint8_t* dstPixels, int dstStride, int dstBitmapHeight, bool onlyAdvance) {
        if (!ensureInit()) return -1;
        if (!onlyAdvance && !dstPixels) return -1;
        if (videoStreamIndex < 0 || !fmt_ctx) return -1;
        for (int retry = 0; retry < 2; ++retry) {
            int ret;
            while ((ret = av_read_frame(fmt_ctx, packet)) >= 0) {
                if (static_cast<int>(packet->stream_index) == videoStreamIndex) {

                    bool colorDecoded = false;
                    if (packet->size > 0) {
                        if (vpx_codec_decode(&vpx_ctx, packet->data, packet->size, nullptr, 0) == VPX_CODEC_OK) {
                            colorDecoded = true;
                        }
                    }

                    if (!colorDecoded) {
                        av_packet_unref(packet);
                        continue;
                    }

                    bool hasAlpha = false;

                    if (vpx_alpha_initialized) {
                        size_t side_data_size = 0;
                        uint8_t* side_data = av_packet_get_side_data(packet, AV_PKT_DATA_MATROSKA_BLOCKADDITIONAL, &side_data_size);

                        if (side_data && side_data_size > 0) {
                            if (side_data_size > 8) {
                                if (vpx_codec_decode(&vpx_alpha_ctx, side_data + 8, side_data_size - 8, nullptr, 0) == VPX_CODEC_OK) {
                                    hasAlpha = true;
                                }
                            }
                            if (!hasAlpha) {
                                if (vpx_codec_decode(&vpx_alpha_ctx, side_data, side_data_size, nullptr, 0) == VPX_CODEC_OK) {
                                    hasAlpha = true;
                                }
                            }
                        }
                    }

                    int delay = 33;
                    AVRational tb = fmt_ctx->streams[videoStreamIndex]->time_base;
                    if (packet->duration > 0) {
                        delay = static_cast<int>(packet->duration * av_q2d(tb) * 1000);
                    } else {
                        AVRational afr = fmt_ctx->streams[videoStreamIndex]->avg_frame_rate;
                        if (afr.num > 0) delay = static_cast<int>(1000.0 * afr.den / afr.num);
                    }
                    if (delay < 10) delay = 33;

                    if (onlyAdvance) {
                        av_packet_unref(packet);
                        frameIndex++;
                        return delay;
                    }

                    vpx_codec_iter_t iter = nullptr;
                    vpx_image_t* img = vpx_codec_get_frame(&vpx_ctx, &iter);

                    vpx_image_t* imgAlpha = nullptr;
                    if (hasAlpha) {
                        vpx_codec_iter_t iterAlpha = nullptr;
                        imgAlpha = vpx_codec_get_frame(&vpx_alpha_ctx, &iterAlpha);
                    }

                    if (img) {
                        int srcW = img->d_w;
                        int srcH = img->d_h;
                        int dstW = renderWidth > 0 ? renderWidth : srcW;
                        int dstH = renderHeight > 0 ? renderHeight : srcH;

                        const uint8_t* yplane = img->planes[0];
                        const uint8_t* uplane = img->planes[1];
                        const uint8_t* vplane = img->planes[2];
                        int ystride = img->stride[0];
                        int ustride = img->stride[1];
                        int vstride = img->stride[2];

                        const uint8_t* aplane = nullptr;
                        int astride = 0;

                        if (imgAlpha) {
                            aplane = imgAlpha->planes[0];
                            astride = imgAlpha->stride[0];
                        }
                        else if ((img->fmt & VPX_IMG_FMT_HAS_ALPHA) && img->planes[3]) {
                            aplane = img->planes[3];
                            astride = img->stride[3];
                        }

                        if (dstW == srcW && dstH == srcH) {
                            if (aplane) {
                                libyuv::I420AlphaToABGR(yplane, ystride, uplane, ustride, vplane, vstride, aplane, astride, dstPixels, dstStride, srcW, srcH, 1);
                            } else {
                                libyuv::I420ToABGR(yplane, ystride, uplane, ustride, vplane, vstride, dstPixels, dstStride, srcW, srcH);
                            }
                        } else {
                            uint8_t* scratch = get_thread_scratch(static_cast<size_t>(srcW) * srcH * 4);

                            if (aplane) {
                                libyuv::I420AlphaToABGR(yplane, ystride, uplane, ustride, vplane, vstride, aplane, astride, scratch, srcW * 4, srcW, srcH, 1);
                            } else {
                                libyuv::I420ToABGR(yplane, ystride, uplane, ustride, vplane, vstride, scratch, srcW * 4, srcW, srcH);
                            }
                            libyuv::ARGBScale(scratch, srcW * 4, srcW, srcH, dstPixels, dstStride, dstW, dstH, libyuv::kFilterLinear);
                        }

                        av_packet_unref(packet);
                        frameIndex++;
                        return delay;
                    }
                    av_packet_unref(packet);
                } else {
                    av_packet_unref(packet);
                }
            }
            if (retry == 0) {
                seekToStart();
                if (onlyAdvance) {
                    return 33;
                }
                continue;
            }
        }
        return -1;
    }

    void seekToStart() {
        if (fmt_ctx && videoStreamIndex >= 0) {
            frameIndex = 0;
            buffer_data.offset = 0;
            if (fmt_ctx->pb) {
                avio_seek(fmt_ctx->pb, 0, SEEK_SET);
                avio_flush(fmt_ctx->pb);
            }
            int ret = av_seek_frame(fmt_ctx, videoStreamIndex, 0, AVSEEK_FLAG_BACKWARD | AVSEEK_FLAG_ANY);
            if (ret < 0) {
                LOGE("Error seeking to start: %d", ret);
            }
            avformat_flush(fmt_ctx);
        }
    }

    void getVideoSize(int* w, int* h) {
        if (videoWidth > 0 && videoHeight > 0) {
            if (w) *w = videoWidth;
            if (h) *h = videoHeight;
        } else {
            if (fmt_ctx && videoStreamIndex >= 0) {
                AVCodecParameters* codecpar = fmt_ctx->streams[videoStreamIndex]->codecpar;
                if (codecpar) {
                    if (w) *w = codecpar->width;
                    if (h) *h = codecpar->height;
                    return;
                }
            }
            if (w) *w = 0;
            if (h) *h = 0;
        }
    }

    void release() {
        if (packet) { av_packet_free(&packet); packet = nullptr; }
        if (vpx_initialized) {
            vpx_codec_destroy(&vpx_ctx);
            vpx_initialized = false;
        }
        if (vpx_alpha_initialized) {
            vpx_codec_destroy(&vpx_alpha_ctx);
            vpx_alpha_initialized = false;
        }
        if (fmt_ctx) { avformat_close_input(&fmt_ctx); fmt_ctx = nullptr; }
        if (avio_ctx) {
            av_freep(&avio_ctx->buffer);
            avio_context_free(&avio_ctx);
            avio_ctx = nullptr;
        }
    }

private:
    std::vector<uint8_t> mBuffer;
    BufferData buffer_data{};
    AVFormatContext* fmt_ctx = nullptr;
    AVIOContext* avio_ctx = nullptr;
    int videoWidth = 0;
    int videoHeight = 0;

    vpx_codec_ctx_t vpx_ctx{};
    vpx_codec_ctx_t vpx_alpha_ctx{};

    bool vpx_initialized = false;
    bool vpx_alpha_initialized = false;

    AVPacket* packet = nullptr;
};

static std::vector<uint8_t> jbyteArrayToVector(JNIEnv* env, jbyteArray array) {
    if (!array) return {};

    jsize len = env->GetArrayLength(array);
    std::vector<uint8_t> vec(len);
    env->GetByteArrayRegion(array, 0, len, reinterpret_cast<jbyte*>(vec.data()));

    return vec;
}

extern "C" JNIEXPORT jlong JNICALL
Java_com_xxcactussell_jni_NativeStickerCore_createWebPHandleFromBytes(
        JNIEnv* env, jobject, jbyteArray data) {

    auto mData = jbyteArrayToVector(env, data);
    if (mData.empty()) return 0;
    auto* instance = new WebPInstance(mData.data(), mData.size());
    return reinterpret_cast<jlong>(instance);
}

extern "C" JNIEXPORT jlong JNICALL
Java_com_xxcactussell_jni_NativeStickerCore_createVpxHandleFromBytes(
        JNIEnv* env, jobject, jbyteArray data) {

    auto mData = jbyteArrayToVector(env, data);
    if (mData.empty()) return 0;

    auto* player = new VpxInstance(mData.data(), mData.size());
    return reinterpret_cast<jlong>(player);
}

extern "C" JNIEXPORT jlong JNICALL
Java_com_xxcactussell_jni_NativeStickerCore_createLottieHandle(
        JNIEnv* env, jobject, jstring jsonStr) {
    const char* json = env->GetStringUTFChars(jsonStr, nullptr);
    auto* instance = new LottieInstance(std::string(json));
    env->ReleaseStringUTFChars(jsonStr, json);
    return reinterpret_cast<jlong>(instance);
}

extern "C" JNIEXPORT void JNICALL
Java_com_xxcactussell_jni_NativeStickerCore_renderLottieWithCache(
        JNIEnv* env, jobject, jlong ptr, jint frame, jobject bitmap) {
    auto* instance = reinterpret_cast<LottieInstance*>(ptr);
    BitmapLocker locker(env, bitmap);
    if (!instance || !locker.isValid()) return;
    int w = instance->renderWidth;
    int h = instance->renderHeight;
    int stride = locker.getInfo().stride;

    std::string key = makeFastKey(instance->sourceHash, frame, w, h);
    std::string filePath = cacheFilePathForKey(key);

    if (retrieveFromCache(key, reinterpret_cast<uint8_t*>(locker.getPixels()), stride, w, h)) return;

    instance->render(frame, locker.getPixels(), stride);
    storeToCache(key, reinterpret_cast<const uint8_t*>(locker.getPixels()), stride, w, h, filePath);
}

extern "C" JNIEXPORT void JNICALL
Java_com_xxcactussell_jni_NativeStickerCore_destroyLottieHandle(
        JNIEnv* env, jobject, jlong ptr) {
    delete reinterpret_cast<LottieInstance*>(ptr);
}

extern "C" JNIEXPORT jint JNICALL
Java_com_xxcactussell_jni_NativeStickerCore_getLottieFrameCount(
        JNIEnv* env, jobject, jlong ptr) {
    auto* instance = reinterpret_cast<LottieInstance*>(ptr);
    return instance ? instance->totalFrames() : 0;
}

extern "C" JNIEXPORT jfloat JNICALL
Java_com_xxcactussell_jni_NativeStickerCore_getLottieFrameRate(
        JNIEnv* env, jobject, jlong ptr) {
    auto* instance = reinterpret_cast<LottieInstance*>(ptr);
    return instance ? instance->frameRate() : 0.0f;
}

extern "C" JNIEXPORT jlong JNICALL
Java_com_xxcactussell_jni_NativeStickerCore_createWebPHandle(
        JNIEnv* env, jobject, jobject byteBuffer) {
    const auto* data = static_cast<const uint8_t*>(env->GetDirectBufferAddress(byteBuffer));
    jlong len = env->GetDirectBufferCapacity(byteBuffer);
    if (data == nullptr || len <= 0) return 0;
    auto* instance = new WebPInstance(data, static_cast<size_t>(len));
    return reinterpret_cast<jlong>(instance);
}

extern "C" JNIEXPORT jint JNICALL
Java_com_xxcactussell_jni_NativeStickerCore_renderWebPWithCache(
        JNIEnv* env, jobject, jlong ptr, jobject bitmap) {
    auto* instance = reinterpret_cast<WebPInstance*>(ptr);
    BitmapLocker locker(env, bitmap);
    if (!instance || !locker.isValid()) return -1;

    int w = instance->renderWidth > 0 ? instance->renderWidth : instance->info.canvas_width;
    int h = instance->renderHeight > 0 ? instance->renderHeight : instance->info.canvas_height;
    int stride = locker.getInfo().stride;

    std::string key = makeFastKey(instance->sourceHash, instance->frameIndex, w, h);
    std::string filePath = cacheFilePathForKey(key);

    if (retrieveFromCache(key, reinterpret_cast<uint8_t*>(locker.getPixels()), stride, w, h)) {
        int delay = instance->renderNext(nullptr, 0, true);
        instance->frameIndex++;
        return (delay > 0) ? delay : 40;
    }

    int delay = instance->renderNext(locker.getPixels(), stride, false);

    if (delay > 0) {
        storeToCache(key, reinterpret_cast<const uint8_t*>(locker.getPixels()), stride, w, h, filePath);
        instance->frameIndex++;
    }
    return delay;
}

extern "C" JNIEXPORT void JNICALL
Java_com_xxcactussell_jni_NativeStickerCore_destroyWebPHandle(
        JNIEnv* env, jobject, jlong ptr) {
    delete reinterpret_cast<WebPInstance*>(ptr);
}

extern "C" JNIEXPORT jlong JNICALL
Java_com_xxcactussell_jni_NativeStickerCore_createVpxHandle(JNIEnv *env, jobject, jobject byteBuffer) {
    const auto* data = static_cast<const uint8_t*>(env->GetDirectBufferAddress(byteBuffer));
    jlong len = env->GetDirectBufferCapacity(byteBuffer);
    if (data == nullptr || len <= 0) return 0;
    auto* player = new VpxInstance(data, static_cast<size_t>(len));
    return reinterpret_cast<jlong>(player);
}

extern "C" JNIEXPORT jint JNICALL
Java_com_xxcactussell_jni_NativeStickerCore_renderVpxWithCache(
        JNIEnv* env, jobject, jlong ptr, jobject jBitmap) {
    auto* instance = reinterpret_cast<VpxInstance*>(ptr);
    if (!instance) return -1;

    BitmapLocker locker(env, jBitmap);
    if (!locker.isValid()) return -1;

    int w = instance->renderWidth;
    int h = instance->renderHeight;
    int stride = locker.getInfo().stride;
    uint8_t* pixels = reinterpret_cast<uint8_t*>(locker.getPixels());

    int currentIdx = instance->frameIndex;

    std::string key = makeFastKey(instance->sourceHash, currentIdx, w, h);
    std::string filePath = cacheFilePathForKey(key);

    if (retrieveFromCache(key, pixels, stride, w, h)) {
        return instance->renderNextFrameAndGetDelay(nullptr, 0, 0, true);
    }

    int delay = instance->renderNextFrameAndGetDelay(pixels, stride, locker.getInfo().height, false);

    if (delay >= 0) {
        storeToCache(key, pixels, stride, w, h, filePath);
    }
    return delay;
}

extern "C" JNIEXPORT void JNICALL
Java_com_xxcactussell_jni_NativeStickerCore_destroyVpxHandle(JNIEnv*, jobject, jlong ptr) {
    delete reinterpret_cast<VpxInstance*>(ptr);
}

extern "C" JNIEXPORT void JNICALL
Java_com_xxcactussell_jni_NativeStickerCore_seekVpxToStart(JNIEnv* env, jobject thiz, jlong ptr) {
    auto* instance = reinterpret_cast<VpxInstance*>(ptr);
    if (instance) instance->seekToStart();
}

void get_size_common(JNIEnv *env, jintArray outArray, int w, int h) {
    if (!outArray || env->GetArrayLength(outArray) < 2) return;
    jint *dims = env->GetIntArrayElements(outArray, nullptr);
    dims[0] = w;
    dims[1] = h;
    env->ReleaseIntArrayElements(outArray, dims, 0);
}

extern "C" JNIEXPORT void JNICALL
Java_com_xxcactussell_jni_NativeStickerCore_getLottieSize(JNIEnv *env, jobject, jlong ptr, jintArray outArray) {
    auto *instance = reinterpret_cast<LottieInstance *>(ptr);
    if (instance) get_size_common(env, outArray, instance->width, instance->height);
}

extern "C" JNIEXPORT void JNICALL
Java_com_xxcactussell_jni_NativeStickerCore_getWebPSize(JNIEnv *env, jobject, jlong ptr, jintArray outArray) {
    auto *instance = reinterpret_cast<WebPInstance *>(ptr);
    if (instance) get_size_common(env, outArray, instance->info.canvas_width, instance->info.canvas_height);
}

extern "C" JNIEXPORT void JNICALL
Java_com_xxcactussell_jni_NativeStickerCore_getVpxSize(JNIEnv *env, jobject, jlong ptr, jintArray outArray) {
    auto *instance = reinterpret_cast<VpxInstance *>(ptr);
    if (instance) {
        int w = 0, h = 0;
        instance->getVideoSize(&w, &h);
        get_size_common(env, outArray, w, h);
    }
}

extern "C" JNIEXPORT void JNICALL
Java_com_xxcactussell_jni_NativeStickerCore_prepareLottieRendering(JNIEnv*, jobject, jlong ptr, jint w, jint h) {
    auto *instance = reinterpret_cast<LottieInstance*>(ptr);
    if (instance) instance->prepareRendering(w, h);
}

extern "C" JNIEXPORT void JNICALL
Java_com_xxcactussell_jni_NativeStickerCore_prepareWebPRendering(JNIEnv*, jobject, jlong ptr, jint w, jint h) {
    auto *instance = reinterpret_cast<WebPInstance*>(ptr);
    if (instance) instance->prepareRendering(w, h);
}

extern "C" JNIEXPORT void JNICALL
Java_com_xxcactussell_jni_NativeStickerCore_prepareVpxRendering(JNIEnv*, jobject, jlong ptr, jint w, jint h) {
    auto *instance = reinterpret_cast<VpxInstance*>(ptr);
    if (instance) instance->prepareRendering(w, h);
}