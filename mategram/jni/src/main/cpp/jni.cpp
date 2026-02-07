#include <jni.h>
#include <android/bitmap.h>
#include <android/hardware_buffer_jni.h>
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
#include "NativeBufferPool.h"
#include "NativeQueueManager.h"

#include <sys/mman.h>
#include <fcntl.h>
#include <unistd.h>
#include <zstd/lib/zstd.h>
#include <unordered_set>

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
#include <sys/stat.h>

#define LOG_TAG "StickerRenderJNI"
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)


static JavaVM* gJavaVM = nullptr;

extern "C" JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM* vm, void* reserved) {
    gJavaVM = vm;
    return JNI_VERSION_1_6;
}

bool fileExists(const std::string& name) {
    struct stat buffer;
    return (stat(name.c_str(), &buffer) == 0);
}

void get_size_common(JNIEnv *env, jintArray outArray, int w, int h) {
    if (!outArray || env->GetArrayLength(outArray) < 2) return;
    jint *dims = env->GetIntArrayElements(outArray, nullptr);
    dims[0] = w;
    dims[1] = h;
    env->ReleaseIntArrayElements(outArray, dims, 0);
}

struct CacheHeader {
    uint32_t magic = 0x4D4348;
    uint16_t version = 1;
    uint16_t width;
    uint16_t height;
    uint32_t stride;
    uint32_t frameCount;
    uint8_t compressionType;
};

void notifyFrameReady(jobject listenerRef, int delay) {
    JNIEnv* env;
    bool attached = false;
    int status = gJavaVM->GetEnv((void**)&env, JNI_VERSION_1_6);

    if (status == JNI_EDETACHED) {
        if (gJavaVM->AttachCurrentThread(&env, nullptr) != JNI_OK) return;
        attached = true;
    } else if (status != JNI_OK) {
        return;
    }

    jclass clazz = env->GetObjectClass(listenerRef);
    if (clazz) {
        jmethodID method = env->GetMethodID(clazz, "onNativeFrameReady", "(I)V");
        if (method) {
            env->CallVoidMethod(listenerRef, method, delay);
            if (env->ExceptionCheck()) env->ExceptionClear();
        }
        env->DeleteLocalRef(clazz);
    }

    if (attached) gJavaVM->DetachCurrentThread();
}


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

static std::string gCacheDir;

extern "C" JNIEXPORT void JNICALL
Java_com_xxcactussell_jni_NativeStickerCore_setCacheDir(JNIEnv* env, jobject, jstring path) {
    const char* p = env->GetStringUTFChars(path, nullptr);
    gCacheDir = std::string(p);
    env->ReleaseStringUTFChars(path, p);
    if (!gCacheDir.empty()) {
        try { std::filesystem::create_directories(gCacheDir); } catch (...) {}
    }
}

extern "C" JNIEXPORT void JNICALL
Java_com_xxcactussell_jni_NativeStickerCore_shutdownCache(JNIEnv*, jobject) {
    stopDiskWorker();
}

class StickerInstance {
public:
    std::recursive_mutex instanceMutex;
    bool isDestroyed = false;
    jobject listenerRef = nullptr;

    virtual ~StickerInstance() {
        if (listenerRef && gJavaVM) {
            JNIEnv* env;
            bool attached = false;
            int status = gJavaVM->GetEnv((void**)&env, JNI_VERSION_1_6);

            if (status == JNI_EDETACHED) {
                if (gJavaVM->AttachCurrentThread(&env, nullptr) == JNI_OK) {
                    attached = true;
                } else {
                    return;
                }
            }

            if (env) env->DeleteGlobalRef(listenerRef);

            if (attached) gJavaVM->DetachCurrentThread();
        }
    }

    virtual void release() = 0;
};

static std::mutex gRegistryMtx;
static std::unordered_map<jlong, std::shared_ptr<StickerInstance>> gInstances;

void registerInstance(jlong ptr, std::shared_ptr<StickerInstance> inst) {
    std::lock_guard<std::mutex> lk(gRegistryMtx);
    gInstances[ptr] = inst;
}

std::shared_ptr<StickerInstance> acquireInstance(jlong ptr) {
    std::lock_guard<std::mutex> lk(gRegistryMtx);
    auto it = gInstances.find(ptr);
    return (it != gInstances.end()) ? it->second : nullptr;
}

void forgetInstance(jlong ptr) {
    std::lock_guard<std::mutex> lk(gRegistryMtx);
    gInstances.erase(ptr);
}

class LottieInstance : public StickerInstance {
public:
    std::unique_ptr<rlottie::Animation> animation{};
    std::unique_ptr<uint32_t> renderBuffer;
    std::string sourceHash;
    std::atomic<int64_t> lastLottieDurationUs{0};
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

    void render(int frame, void* dstPixels, int dstStride) {
        if (!animation || !renderBuffer) return;

        auto start = std::chrono::high_resolution_clock::now();

        rlottie::Surface surface(renderBuffer.get(), renderWidth, renderHeight, renderWidth * 4);
        animation->renderSync(frame, surface, true);

        const auto* src = reinterpret_cast<const uint8_t*>(renderBuffer.get());
        libyuv::ARGBToABGR(src, renderWidth * 4, reinterpret_cast<uint8_t*>(dstPixels), dstStride, renderWidth, renderHeight);

        auto end = std::chrono::high_resolution_clock::now();
        int64_t duration = std::chrono::duration_cast<std::chrono::microseconds>(end - start).count();
        lastLottieDurationUs.store(duration, std::memory_order_relaxed);
    }

    void release() override {
        std::lock_guard<std::recursive_mutex> lock(instanceMutex);
        if (isDestroyed) return;
        isDestroyed = true;
        animation.reset();
        renderBuffer.reset();
    }

    int totalFrames() const { return animation ? animation->totalFrame() : 0; }
    float frameRate() const { return animation ? animation->frameRate() : 0.0f; }
};

class WebPInstance : public StickerInstance{
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
            options.use_threads = 1;
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

    void release() override {
        std::lock_guard<std::recursive_mutex> lock(instanceMutex);
        if (isDestroyed) return;
        isDestroyed = true;
        if (dec) {
            WebPAnimDecoderDelete(dec);
            dec = nullptr;
        }
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
        libyuv::ARGBAttenuate(frameRGBA, srcW * 4, dst, dstStride, srcW, srcH);
    } else {
        uint8_t* scratch = gScratchPool.get(static_cast<size_t>(srcW) * srcH * 4);
        libyuv::ARGBAttenuate(frameRGBA, srcW * 4, scratch, srcW * 4, srcW, srcH);
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

class VpxInstance : public StickerInstance{
public:
    std::string sourceHash;
    int renderWidth = 0;
    int renderHeight = 0;
    std::once_flag initFlag;
    std::atomic<bool> initialized{false};
    int videoStreamIndex = -1;
    int frameIndex = 0;
    std::atomic<int64_t> lastDecodeDurationUs{0};

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
            // LOGE("VPX: Failed to init alpha decoder");
        } else {
            vpx_alpha_initialized = true;
        }

        if (vpx_initialized) {
            vpx_codec_control(&vpx_ctx, VP9_DECODE_SVC_SPATIAL_LAYER, 0);
        }

        if (codecpar->codec_id == AV_CODEC_ID_VP9) {
            vpx_codec_control(&vpx_ctx, VP9_SET_SKIP_LOOP_FILTER, 1);
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

    int renderNextFrameAndGetDelay(uint8_t* dstPixels, int dstStride, bool onlyAdvance) {
        if (!ensureInit()) return -1;
        if (!onlyAdvance && !dstPixels) return -1;
        if (videoStreamIndex < 0 || !fmt_ctx) return -1;

        if (isDestroyed || !fmt_ctx) return -1;

        auto start = std::chrono::high_resolution_clock::now();
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
                        auto end = std::chrono::high_resolution_clock::now();
                        int64_t duration = std::chrono::duration_cast<std::chrono::microseconds>(end - start).count();
                        lastDecodeDurationUs.store(duration, std::memory_order_relaxed);

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
                // LOGE("Error seeking to start: %d", ret);
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
        std::lock_guard<std::recursive_mutex> lock(instanceMutex);
        if (isDestroyed) return;
        isDestroyed = true;

        if (fmt_ctx) {
            avformat_close_input(&fmt_ctx);
            fmt_ctx = nullptr;
        }
        if (packet) { av_packet_free(&packet); packet = nullptr; }
        if (vpx_initialized) { vpx_codec_destroy(&vpx_ctx); vpx_initialized = false; }
        if (avio_ctx) {
            if (avio_ctx->buffer) av_freep(&avio_ctx->buffer);
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

extern "C" {
JNIEXPORT jobject JNICALL
Java_com_xxcactussell_jni_NativeStickerCore_acquireNativeBuffer(JNIEnv *env, jobject, jint w,
                                                                jint h, jlongArray outPtr) {
    NativeBuffer *nb = gNativePool.acquire(w, h);
    if (!nb) return nullptr;

    jlong *ptrArr = env->GetLongArrayElements(outPtr, nullptr);
    ptrArr[0] = reinterpret_cast<jlong>(nb);
    env->ReleaseLongArrayElements(outPtr, ptrArr, 0);

    return AHardwareBuffer_toHardwareBuffer(env, nb->hwBuffer);
}

JNIEXPORT void JNICALL
Java_com_xxcactussell_jni_NativeStickerCore_releaseNativeBuffer(JNIEnv *, jobject, jlong ptr) {
    if (ptr) gNativePool.release(reinterpret_cast<NativeBuffer *>(ptr));
}
}

extern "C" JNIEXPORT jlong JNICALL
Java_com_xxcactussell_jni_NativeStickerCore_createWebPHandleFromBytes(JNIEnv* env, jobject, jbyteArray data) {
    auto mData = jbyteArrayToVector(env, data);
    if (mData.empty()) return 0;
    auto inst = std::make_shared<WebPInstance>(mData.data(), mData.size());
    jlong ptr = reinterpret_cast<jlong>(inst.get());
    registerInstance(ptr, inst);
    return ptr;
}

extern "C" JNIEXPORT jlong JNICALL
Java_com_xxcactussell_jni_NativeStickerCore_createVpxHandleFromBytes(JNIEnv* env, jobject, jbyteArray data) {
    auto mData = jbyteArrayToVector(env, data);
    if (mData.empty()) return 0;
    auto inst = std::make_shared<VpxInstance>(mData.data(), mData.size());
    jlong ptr = reinterpret_cast<jlong>(inst.get());
    registerInstance(ptr, inst);
    return ptr;
}

extern "C" JNIEXPORT jlong JNICALL
Java_com_xxcactussell_jni_NativeStickerCore_createLottieHandle(JNIEnv* env, jobject, jstring jsonStr) {
    const char* json = env->GetStringUTFChars(jsonStr, nullptr);
    auto inst = std::make_shared<LottieInstance>(std::string(json));
    env->ReleaseStringUTFChars(jsonStr, json);
    jlong ptr = reinterpret_cast<jlong>(inst.get());
    registerInstance(ptr, inst);
    return ptr;
}

extern "C" JNIEXPORT void JNICALL
Java_com_xxcactussell_jni_NativeStickerCore_destroyLottieHandle(JNIEnv* env, jobject, jlong ptr) {
    auto inst = acquireInstance(ptr);
    if (inst) {
        inst->release();
        forgetInstance(ptr);
    }
}

extern "C" JNIEXPORT void JNICALL
Java_com_xxcactussell_jni_NativeStickerCore_destroyWebPHandle(JNIEnv* env, jobject, jlong ptr) {
    auto inst = acquireInstance(ptr);
    if (inst) {
        inst->release();
        forgetInstance(ptr);
    }
}

extern "C" JNIEXPORT void JNICALL
Java_com_xxcactussell_jni_NativeStickerCore_destroyVpxHandle(JNIEnv* env, jobject, jlong ptr) {
    auto inst = acquireInstance(ptr);
    if (inst) {
        inst->release();
        forgetInstance(ptr);
    }
}

extern "C" JNIEXPORT jint JNICALL
Java_com_xxcactussell_jni_NativeStickerCore_getLottieFrameCount(JNIEnv* env, jobject, jlong ptr) {
    auto inst = std::dynamic_pointer_cast<LottieInstance>(acquireInstance(ptr));
    return inst ? inst->totalFrames() : 0;
}

extern "C" JNIEXPORT jfloat JNICALL
Java_com_xxcactussell_jni_NativeStickerCore_getLottieFrameRate(JNIEnv* env, jobject, jlong ptr) {
    auto inst = std::dynamic_pointer_cast<LottieInstance>(acquireInstance(ptr));
    return inst ? inst->frameRate() : 0.0f;
}

extern "C" JNIEXPORT void JNICALL
Java_com_xxcactussell_jni_NativeStickerCore_getLottieSize(JNIEnv *env, jobject, jlong ptr, jintArray outArray) {
    auto inst = std::dynamic_pointer_cast<LottieInstance>(acquireInstance(ptr));
    if (inst) get_size_common(env, outArray, inst->width, inst->height);
}

extern "C" JNIEXPORT void JNICALL
Java_com_xxcactussell_jni_NativeStickerCore_getWebPSize(JNIEnv *env, jobject, jlong ptr, jintArray outArray) {
    auto inst = std::dynamic_pointer_cast<WebPInstance>(acquireInstance(ptr));
    if (inst) get_size_common(env, outArray, inst->info.canvas_width, inst->info.canvas_height);
}

extern "C" JNIEXPORT void JNICALL
Java_com_xxcactussell_jni_NativeStickerCore_getVpxSize(JNIEnv *env, jobject, jlong ptr, jintArray outArray) {
    auto inst = std::dynamic_pointer_cast<VpxInstance>(acquireInstance(ptr));
    if (inst) {
        int w = 0, h = 0;
        inst->getVideoSize(&w, &h);
        get_size_common(env, outArray, w, h);
    }
}

extern "C" JNIEXPORT void JNICALL
Java_com_xxcactussell_jni_NativeStickerCore_prepareLottieRendering(JNIEnv*, jobject, jlong ptr, jint w, jint h) {
    auto inst = std::dynamic_pointer_cast<LottieInstance>(acquireInstance(ptr));
    if (inst) inst->prepareRendering(w, h);
}

extern "C" JNIEXPORT void JNICALL
Java_com_xxcactussell_jni_NativeStickerCore_prepareWebPRendering(JNIEnv*, jobject, jlong ptr, jint w, jint h) {
    auto inst = std::dynamic_pointer_cast<WebPInstance>(acquireInstance(ptr));
    if (inst) inst->prepareRendering(w, h);
}

extern "C" JNIEXPORT void JNICALL
Java_com_xxcactussell_jni_NativeStickerCore_prepareVpxRendering(JNIEnv*, jobject, jlong ptr, jint w, jint h) {
    auto inst = std::dynamic_pointer_cast<VpxInstance>(acquireInstance(ptr));
    if (inst) inst->prepareRendering(w, h);
}

extern "C" JNIEXPORT void JNICALL
Java_com_xxcactussell_jni_NativeStickerCore_renderAsync(JNIEnv* env, jobject, jint type, jlong ptr, jlong bufPtr, jint frame, jstring cachePath, jobject listener) {
    const char* cPath = env->GetStringUTFChars(cachePath, nullptr);
    std::string path(cPath ? cPath : "");
    if (cPath) env->ReleaseStringUTFChars(cachePath, cPath);

    auto inst = acquireInstance(ptr);
    if (!inst) {
        return;
    }

    {
        std::lock_guard<std::recursive_mutex> lock(inst->instanceMutex);
        if (inst->listenerRef == nullptr) {
            inst->listenerRef = env->NewGlobalRef(listener);
        }
    }

    gQueueManager.enqueue(StickerPriority::STICKER, [type, inst, bufPtr, frame, path]() {
        if (!gJavaVM) return;
        JNIEnv* localEnv;
        bool attached = false;
        int status = gJavaVM->GetEnv((void**)&localEnv, JNI_VERSION_1_6);
        if (status == JNI_EDETACHED) {
            if (gJavaVM->AttachCurrentThread(&localEnv, nullptr) != JNI_OK) return;
            attached = true;
        } else if (status != JNI_OK) {
            return;
        }

        auto* nb = reinterpret_cast<NativeBuffer*>(bufPtr);
        int delay = -1;

        if (nb && nb->hwBuffer) {
            AHardwareBuffer_Desc desc;
            AHardwareBuffer_describe(nb->hwBuffer, &desc);
            int byteStride = desc.stride * 4;
            size_t expectedSize = byteStride * desc.height;

            void* pixels = nullptr;
            if (AHardwareBuffer_lock(nb->hwBuffer, AHARDWAREBUFFER_USAGE_CPU_WRITE_RARELY, -1, nullptr, &pixels) == 0) {
                uint8_t* dst = reinterpret_cast<uint8_t*>(pixels);
                bool loadedFromCache = false;

                if (!path.empty() && fileExists(path)) {
                    int fd = open(path.c_str(), O_RDONLY);
                    if (fd >= 0) {
                        struct stat st;
                        if (fstat(fd, &st) == 0 && st.st_size > sizeof(CacheHeader)) {
                            void* mmapped = mmap(nullptr, st.st_size, PROT_READ, MAP_PRIVATE, fd, 0);
                            if (mmapped != MAP_FAILED) {
                                CacheHeader* header = (CacheHeader*)mmapped;
                                if (header->magic == 0x4D4348 && header->width == desc.width) {
                                    if (!ZSTD_isError(ZSTD_decompress(dst, expectedSize, (uint8_t*)mmapped + sizeof(CacheHeader), st.st_size - sizeof(CacheHeader)))) {
                                        loadedFromCache = true;
                                        delay = (type == 0) ? 0 : 33;
                                    }
                                }
                                munmap(mmapped, st.st_size);
                            }
                        }
                        close(fd);
                    }
                }

                if (!loadedFromCache) {
                    std::lock_guard<std::recursive_mutex> lock(inst->instanceMutex);
                    if (!inst->isDestroyed) {
                        if (type == 0) {
                            auto lottie = std::dynamic_pointer_cast<LottieInstance>(inst);
                            if (lottie) { lottie->render(frame, dst, byteStride); delay = 0; }
                        } else if (type == 1) {
                            auto webp = std::dynamic_pointer_cast<WebPInstance>(inst);
                            if (webp) { delay = webp->renderNext(dst, byteStride, false); webp->frameIndex++; }
                        } else if (type == 2) {
                            auto vpx = std::dynamic_pointer_cast<VpxInstance>(inst);
                            if (vpx) { delay = vpx->renderNextFrameAndGetDelay(dst, byteStride, false); }
                        }

                        if (delay >= 0 && !path.empty()) {
                            std::vector<uint8_t> snapshot(dst, dst + expectedSize);
                            uint32_t w = desc.width, h = desc.height;
                            int s = desc.stride;
                            gQueueManager.enqueue(StickerPriority::EMOJI, [path, snapshot, w, h, s]() {
                                size_t cBound = ZSTD_compressBound(snapshot.size());
                                std::vector<uint8_t> cBuff(cBound);
                                size_t cSize = ZSTD_compress(cBuff.data(), cBound, snapshot.data(), snapshot.size(), 3);
                                if (!ZSTD_isError(cSize)) {
                                    std::ofstream ofs(path, std::ios::binary);
                                    CacheHeader hdr; hdr.width = (uint16_t)w; hdr.height = (uint16_t)h; hdr.stride = (uint32_t)s;
                                    ofs.write((char*)&hdr, sizeof(CacheHeader));
                                    ofs.write((char*)cBuff.data(), cSize);
                                }
                            });
                        }
                    }
                }
                AHardwareBuffer_unlock(nb->hwBuffer, nullptr);
            }
        }
        if (delay >= 0 && inst->listenerRef) {
            notifyFrameReady(inst->listenerRef, delay);
        }

        if (attached) gJavaVM->DetachCurrentThread();
    });
}

extern "C" JNIEXPORT jlong JNICALL
Java_com_xxcactussell_jni_NativeStickerCore_getLastDecodeDurationUs(JNIEnv*, jobject, jint type, jlong ptr) {
    if (type == 0) { // Lottie
        auto* instance = reinterpret_cast<LottieInstance*>(ptr);
        return instance ? instance->lastLottieDurationUs.load(std::memory_order_relaxed) : 0;
    } else if (type == 2) { // VPX
        auto* instance = reinterpret_cast<VpxInstance*>(ptr);
        return instance ? instance->lastDecodeDurationUs.load(std::memory_order_relaxed) : 0;
    }
    return 0;
}