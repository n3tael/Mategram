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

// Библиотеки
#include "rlottie.h"
#include "webp/decode.h"
#include "webp/demux.h" // ВАЖНО: Добавлена для анимации WebP (как в Telegram)
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

// --- Utility Functions (Hashing, Hex, etc.) ---

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

// --- Disk Cache Worker ---

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
    while (gDiskWorkerRunning.load()) {
        if (gDiskQueue.empty()) {
            gDiskQueueCv.wait(lk);
            continue;
        }
        DiskWriteTask task = std::move(gDiskQueue.front());
        gDiskQueue.pop_front();
        lk.unlock();
        {
            std::ofstream ofs(task.path, std::ios::binary | std::ios::trunc);
            if (ofs) ofs.write(reinterpret_cast<const char*>(task.payload.data()), task.payload.size());
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

// --- Caching Logic ---

static std::mutex gCacheMutex;
struct CacheEntry {
    std::shared_ptr<std::vector<uint8_t>> data;
    size_t w;
    size_t h;
    std::chrono::steady_clock::time_point lastAccess;
};
static std::unordered_map<std::string, CacheEntry> gCache;
static size_t gCacheSizeBytes = 0;
static size_t gCacheMaxBytes = 200ULL * 1024ULL * 1024ULL;
static std::string gCacheDir;

static void ensureCacheDirExists() {
    if (gCacheDir.empty()) return;
    try {
        std::filesystem::create_directories(gCacheDir);
    } catch (...) {}
}

static void evictIfNeeded() {
    if (gCacheSizeBytes <= gCacheMaxBytes) return;
    std::vector<std::pair<std::string, std::chrono::steady_clock::time_point>> items;
    for (auto &p : gCache) items.emplace_back(p.first, p.second.lastAccess);
    std::sort(items.begin(), items.end(), [](auto &a, auto &b){ return a.second < b.second; });
    for (auto &it : items) {
        auto found = gCache.find(it.first);
        if (found == gCache.end()) continue;
        gCacheSizeBytes -= found->second.data->size();
        gCache.erase(found);
        if (gCacheSizeBytes <= gCacheMaxBytes) break;
    }
}

static std::string cacheFilePathForKey(const std::string& key) {
    if (gCacheDir.empty()) return "";
    uint64_t h64 = fnv1a_hash64(reinterpret_cast<const uint8_t*>(key.data()), key.size());
    std::string hex = u64_to_hex(h64);
    char fname[128];
    snprintf(fname, sizeof(fname), "stcache_%s.bin", hex.c_str());
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
        int w = 0, h = 0;
        ifs.read(reinterpret_cast<char*>(&w), sizeof(int));
        ifs.read(reinterpret_cast<char*>(&h), sizeof(int));
        if (w <= 0 || h <= 0) return false;
        size_t bytes = static_cast<size_t>(w) * h * 4;
        auto buf = std::make_shared<std::vector<uint8_t>>(bytes);
        ifs.read(reinterpret_cast<char*>(buf->data()), bytes);
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
    std::lock_guard<std::mutex> lk(gCacheMutex);
    auto it = gCache.find(key);
    if (it != gCache.end()) {
        auto& ce = it->second;
        ce.lastAccess = std::chrono::steady_clock::now();
        const auto& buf = *ce.data;
        for (int y = 0; y < h; ++y) {
            memcpy(dst + static_cast<size_t>(y) * dstStride, buf.data() + static_cast<size_t>(y) * w * 4, static_cast<size_t>(w) * 4);
        }
        return true;
    }
    std::shared_ptr<std::vector<uint8_t>> diskBuf;
    int dw = 0, dh = 0;
    if (loadCacheFromDisk(key, diskBuf, dw, dh) && diskBuf) {
        if (dw == w && dh == h) {
            auto entry = CacheEntry{diskBuf, static_cast<size_t>(dw), static_cast<size_t>(dh), std::chrono::steady_clock::now()};
            gCache[key] = entry;
            gCacheSizeBytes += diskBuf->size();
            evictIfNeeded();
            const auto& buf = *diskBuf;
            for (int y = 0; y < h; ++y) {
                memcpy(dst + static_cast<size_t>(y) * dstStride, buf.data() + static_cast<size_t>(y) * w * 4, static_cast<size_t>(w) * 4);
            }
            return true;
        }
    }
    return false;
}

static std::string makeCacheKeyFromHash(const char* prefix, const std::string& sourceHash, int frame, int w, int h) {
    char buf[256];
    snprintf(buf, sizeof(buf), "%s:%s:%d:%dx%d", prefix, sourceHash.c_str(), frame, w, h);
    return std::string(buf);
}

static const size_t MAX_LOTTIE_FRAMES_PER_INSTANCE = 40;
static std::unordered_map<std::string, int> gFramesCachedPerSource;
static std::mutex gFramesMutex;

static void storeToCache(const std::string& key, const uint8_t* src, int srcStride, int w, int h, const std::string& filePath) {
    size_t bytes = static_cast<size_t>(w) * h * 4;
    auto buf = std::make_shared<std::vector<uint8_t>>(bytes);
    for (int y = 0; y < h; ++y) {
        memcpy(buf->data() + static_cast<size_t>(y) * w * 4, src + static_cast<size_t>(y) * srcStride, static_cast<size_t>(w) * 4);
    }
    {
        std::lock_guard<std::mutex> lk(gCacheMutex);
        gCacheSizeBytes += buf->size();
        gCache[key] = CacheEntry{buf, static_cast<size_t>(w), static_cast<size_t>(h), std::chrono::steady_clock::now()};
        evictIfNeeded();
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
        // Apply limiter to all types to prevent disk trashing
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

// --- Bitmap Helper ---

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

// --- Lottie Implementation (UNCHANGED as requested) ---

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
            renderBuffer = std::unique_ptr<uint32_t>(new uint32_t[static_cast<size_t>(renderWidth) * renderHeight]);
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
        libyuv::ARGBToABGR(src, srcStride,
                           reinterpret_cast<uint8_t*>(dstPixels), dstStride,
                           renderWidth, renderHeight);
    }
    int totalFrames() const { return animation ? animation->totalFrame() : 0; }
    float frameRate() const { return animation ? animation->frameRate() : 0.0f; }
};

// --- WebP Implementation (ADAPTED to Telegram Style - Animation API) ---

class WebPInstance {
public:
    WebPAnimDecoder* dec = nullptr;
    WebPAnimInfo info{};
    std::vector<uint8_t> mData; // Keep source data alive
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
            // Telegram uses MODE_RGBA or BGRA depending on platform.
            // Android Bitmaps are usually RGBA_8888 (which is ABGR or RGBA in memory).
            // libwebp outputs RGBA order by default.
            options.color_mode = MODE_RGBA;
            options.use_threads = 1;
            dec = WebPAnimDecoderNew(&webp_data, &options);
            if (dec) {
                WebPAnimDecoderGetInfo(dec, &info);
            }
        }
    }

    ~WebPInstance() {
        if (dec) {
            WebPAnimDecoderDelete(dec);
            dec = nullptr;
        }
    }

    void prepareRendering(int w, int h) {
        renderWidth = w;
        renderHeight = h;
    }

    // Returns delay for next frame in ms
    int renderNext(void* dstPixels, int dstStride) {
        if (!dec) return -1;

        uint8_t* frameRGBA = nullptr;
        int timestamp = 0;

        if (!WebPAnimDecoderGetNext(dec, &frameRGBA, &timestamp)) {
            // Animation finished, reset to start
            WebPAnimDecoderReset(dec);
            lastTimestamp = 0;
            frameIndex = 0;
            if (!WebPAnimDecoderGetNext(dec, &frameRGBA, &timestamp)) {
                return -1;
            }
        }

        int delay = timestamp - lastTimestamp;
        if (delay <= 0) delay = 16; // Fallback
        lastTimestamp = timestamp;

        int srcW = info.canvas_width;
        int srcH = info.canvas_height;
        int dstW = renderWidth > 0 ? renderWidth : srcW;
        int dstH = renderHeight > 0 ? renderHeight : srcH;

        uint8_t* dst = reinterpret_cast<uint8_t*>(dstPixels);

        // WebP output is RGBA. Android Bitmap (RGBA_8888) is usually ABGR in little-endian.
        // Telegram typically uses libyuv to scale and convert if needed.

        if (srcW == dstW && srcH == dstH) {
            // Simple Copy + optional conversion if colors look wrong (ARGB vs ABGR)
            // Assuming standard RGBA output from decoder maps to Android expectations for now
            // But if colors are swapped (blue/red), use libyuv::ARGBToABGR
            // libwebp outputs R,G,B,A order in memory.
            libyuv::ARGBCopy(frameRGBA, srcW * 4,
                             dst, dstStride,
                             srcW, srcH);
        } else {
            // Scale using libyuv
            libyuv::ARGBScale(frameRGBA, srcW * 4, srcW, srcH,
                              dst, dstStride, dstW, dstH,
                              libyuv::kFilterBilinear);
        }

        return delay;
    }
};

// --- VPx / WebM Implementation (ADAPTED to Telegram Style - Alpha Handling) ---

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

        // 1. Инициализация основного декодера (Цвет)
        vpx_codec_dec_cfg_t cfg = {};
        cfg.threads = 1; // Лучше 1 поток на каждый контекст для стикеров, чтобы не оверхедить
        cfg.w = codecpar->width;
        cfg.h = codecpar->height;

        if (vpx_codec_dec_init_ver(&vpx_ctx, chosen, &cfg, 0, VPX_DECODER_ABI_VERSION) != VPX_CODEC_OK) {
            return false;
        }
        vpx_initialized = true;

        // 2. Инициализация альфа-декодера (Маска)
        // Используем тот же интерфейс, так как альфа обычно кодируется тем же кодеком
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

    int renderNextFrameAndGetDelay(uint8_t* dstPixels, int dstStride, int dstBitmapHeight) {
        if (!ensureInit()) return -1;
        if (!dstPixels) return -1;
        if (videoStreamIndex < 0 || !fmt_ctx) return -1;

        int ret;
        while ((ret = av_read_frame(fmt_ctx, packet)) >= 0) {
            if (static_cast<int>(packet->stream_index) == videoStreamIndex) {

                // 1. Декодируем основной цвет (Color)
                bool colorDecoded = false;
                if (packet->size > 0) {
                    if (vpx_codec_decode(&vpx_ctx, packet->data, packet->size, nullptr, 0) == VPX_CODEC_OK) {
                        colorDecoded = true;
                    } else {
                        // Лог ошибки цвета, если нужно
                        // LOGE("VPX: Color decode failed");
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

                        if (!hasAlpha) {
                            LOGD("VPX: SideData found (%zu bytes) but decode failed. First bytes: %02x %02x %02x %02x",
                                 side_data_size, side_data[0], side_data[1], side_data[2], side_data[3]);
                        }
                    }
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
                            libyuv::I420AlphaToABGR(
                                    yplane, ystride,
                                    uplane, ustride,
                                    vplane, vstride,
                                    aplane, astride,
                                    dstPixels, dstStride,
                                    srcW, srcH,
                                    0);
                        } else {
                            libyuv::I420ToABGR(
                                    yplane, ystride,
                                    uplane, ustride,
                                    vplane, vstride,
                                    dstPixels, dstStride,
                                    srcW, srcH);
                        }
                    } else {
                        // Масштабирование
                        size_t tmpSize = static_cast<size_t>(srcW) * srcH * 4;
                        if (tmpBuf.size() < tmpSize) tmpBuf.resize(tmpSize);

                        if (aplane) {
                            libyuv::I420AlphaToABGR(
                                    yplane, ystride,
                                    uplane, ustride,
                                    vplane, vstride,
                                    aplane, astride,
                                    tmpBuf.data(), srcW * 4,
                                    srcW, srcH,
                                    0);
                        } else {
                            libyuv::I420ToABGR(
                                    yplane, ystride,
                                    uplane, ustride,
                                    vplane, vstride,
                                    tmpBuf.data(), srcW * 4,
                                    srcW, srcH);
                        }

                        libyuv::ARGBScale(tmpBuf.data(), srcW * 4, srcW, srcH,
                                          dstPixels, dstStride, dstW, dstH,
                                          libyuv::kFilterBilinear);
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

                    av_packet_unref(packet);
                    frameIndex++;
                    return delay;
                }

                av_packet_unref(packet);
            } else {
                av_packet_unref(packet);
            }
        }

        seekToStart();
        return 0;
    }

    void seekToStart() {
        if (fmt_ctx && videoStreamIndex >= 0) {
            av_seek_frame(fmt_ctx, videoStreamIndex, 0, AVSEEK_FLAG_BACKWARD);
            buffer_data.offset = 0;
            frameIndex = 0;

            if (vpx_initialized) {
                vpx_codec_destroy(&vpx_ctx);
                vpx_initialized = false;
            }
            if (vpx_alpha_initialized) {
                vpx_codec_destroy(&vpx_alpha_ctx);
                vpx_alpha_initialized = false;
            }

            if (iface) {
                vpx_codec_dec_cfg_t cfg = {};
                cfg.threads = 1;
                vpx_codec_dec_init_ver(&vpx_ctx, iface, &cfg, 0, VPX_DECODER_ABI_VERSION);
                vpx_initialized = true;
                vpx_codec_dec_init_ver(&vpx_alpha_ctx, iface, &cfg, 0, VPX_DECODER_ABI_VERSION);
                vpx_alpha_initialized = true;
            }
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
    std::vector<uint8_t> tmpBuf; // Буфер для ресайза
    BufferData buffer_data{};
    AVFormatContext* fmt_ctx = nullptr;
    AVIOContext* avio_ctx = nullptr;
    int videoWidth = 0;
    int videoHeight = 0;

    vpx_codec_ctx_t vpx_ctx{};
    vpx_codec_ctx_t vpx_alpha_ctx{};

    vpx_codec_iface_t* iface = nullptr;
    bool vpx_initialized = false;
    bool vpx_alpha_initialized = false;

    AVPacket* packet = nullptr;
};

// --- JNI Export Wrapper Functions ---

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

    std::string key = makeCacheKeyFromHash("lottie", instance->sourceHash, frame, w, h);
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

    // Use current frame index for cache key
    std::string key = makeCacheKeyFromHash("webp", instance->sourceHash, instance->frameIndex, w, h);
    std::string filePath = cacheFilePathForKey(key);

    if (retrieveFromCache(key, reinterpret_cast<uint8_t*>(locker.getPixels()), stride, w, h)) {
        // Cache hit. We still need to advance decoder state ideally, but since we are caching "frames"
        // in sequence, logic implies we are at the right frame.
        // For simplicity with cache: increment index to simulate progress.
        // NOTE: Mixed mode (cache + live decoder) is tricky.
        instance->frameIndex++;
        return 40; // Default delay if retrieved from cache without decoding info
    }

    int delay = instance->renderNext(locker.getPixels(), stride);

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
    BitmapLocker locker(env, jBitmap);
    if (!instance || !locker.isValid()) return -1;

    int w = instance->renderWidth;
    int h = instance->renderHeight;
    // Ensure size is known (init triggered if needed)
    if (w <= 0 || h <= 0) {
        int vw = 0, vh = 0;
        instance->getVideoSize(&vw, &vh);
        if (vw > 0 && vh > 0) { w = vw; h = vh; } else return -1;
    }

    int frameIdx = instance->frameIndex;
    int stride = locker.getInfo().stride;
    uint8_t* pixels = reinterpret_cast<uint8_t*>(locker.getPixels());

    std::string key = makeCacheKeyFromHash("webm", instance->sourceHash, frameIdx, w, h);
    std::string filePath = cacheFilePathForKey(key);

    if (retrieveFromCache(key, pixels, stride, w, h)) {
        instance->frameIndex++;
        return 40;
    }

    int delay = instance->renderNextFrameAndGetDelay(pixels, stride, locker.getInfo().height);
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