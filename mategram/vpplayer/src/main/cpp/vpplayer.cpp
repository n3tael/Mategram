#include <jni.h>
#include <android/log.h>
#include <android/native_window.h>
#include <android/native_window_jni.h>
#include <thread>
#include <atomic>
#include <chrono>
#include <cstdio>
#include <cstring>
#include <string>
#include <mutex>
#include <utility>
#include <vector>
#include <cassert>
#include <memory>
#include <map>

extern "C" {
#include "libffmpeg-prebuilt/include/libavformat/avformat.h"
#include "libffmpeg-prebuilt/include/libavcodec/avcodec.h"
#include "libffmpeg-prebuilt/include/libavutil/imgutils.h"
#include "libffmpeg-prebuilt/include/libavutil/avutil.h"
#include "libffmpeg-prebuilt/include/libavutil/frame.h"
}

#include "libyuv-prebuilt/include/libyuv.h"

class FFmpegPlayer : public std::enable_shared_from_this<FFmpegPlayer> {
public:
    FFmpegPlayer(ANativeWindow* window, std::string  videoPath)
            : mWindow(window), mVideoPath(std::move(videoPath)), mStopDecoding(false), mThreadFinished(false) { }

    ~FFmpegPlayer() {
        __android_log_print(ANDROID_LOG_DEBUG, "VPPlayer", "FFmpegPlayer destructor called. (Handle: %p)", (void*)this);
        if (mWindow) {
            __android_log_print(ANDROID_LOG_DEBUG, "VPPlayer", "FFmpegPlayer destructor: releasing ANativeWindow %p.", mWindow);
            ANativeWindow_release(mWindow);
            mWindow = nullptr;
            __android_log_print(ANDROID_LOG_DEBUG, "VPPlayer", "FFmpegPlayer destructor: ANativeWindow released.");
        } else {
            __android_log_print(ANDROID_LOG_DEBUG, "VPPlayer", "FFmpegPlayer destructor: mWindow is null, no release needed.");
        }
        __android_log_print(ANDROID_LOG_DEBUG, "VPPlayer", "FFmpegPlayer destructor finished. (Handle: %p)", (void*)this);
    }

    void start() {
        mStopDecoding = false;
        mThreadFinished = false;
        mDecodingThread = std::thread([self = shared_from_this()]() {
            self->decodingLoop();
            self->mThreadFinished = true;
            __android_log_print(ANDROID_LOG_DEBUG, "VPPlayer", "Decoding thread finished and set mThreadFinished to true.");
        });
    }

    void stop() {
        mStopDecoding = true;
    }
    std::thread& getDecodingThread() { return mDecodingThread; }
    std::atomic<bool>& getThreadFinishedFlag() { return mThreadFinished; }


private:
    static int64_t getFrameTimestampMs(AVFrame* frame, AVStream* stream) {
        if (frame->pts != AV_NOPTS_VALUE) {
            AVRational ms_time_base = {1, 1000};
            return av_rescale_q(frame->pts, stream->time_base, ms_time_base);
        }
        return 0;
    }

    void decodingLoop() {
        AVFormatContext* fmt_ctx = nullptr;
        AVCodecContext* codec_ctx = nullptr;
        AVFrame* frame = nullptr;
        AVPacket* packet = nullptr;
        std::vector<uint8_t> argbBuffer;
        auto startTime = std::chrono::steady_clock::now();
        bool success = false;

        do {
            if (avformat_open_input(&fmt_ctx, mVideoPath.c_str(), nullptr, nullptr) != 0) {
                __android_log_print(ANDROID_LOG_ERROR, "VPPlayer", "Failed to open input for %s", mVideoPath.c_str());
                break;
            }

            if (avformat_find_stream_info(fmt_ctx, nullptr) < 0) {
                __android_log_print(ANDROID_LOG_ERROR, "VPPlayer", "Failed to find stream info");
                break;
            }

            int videoStreamIndex = -1;
            for (unsigned int i = 0; i < fmt_ctx->nb_streams; ++i) {
                if (fmt_ctx->streams[i]->codecpar->codec_type == AVMEDIA_TYPE_VIDEO) {
                    videoStreamIndex = static_cast<int>(i);
                    break;
                }
            }
            if (videoStreamIndex == -1) {
                __android_log_print(ANDROID_LOG_ERROR, "VPPlayer", "No video stream found");
                break;
            }

            AVStream* videoStream = fmt_ctx->streams[videoStreamIndex];
            AVCodecParameters* codecpar = videoStream->codecpar;
            const AVCodec* codec = avcodec_find_decoder_by_name("libvpx-vp9");
            if (!codec) {
                __android_log_print(ANDROID_LOG_ERROR, "VPPlayer", "VP9 decoder not found");
                break;
            }

            codec_ctx = avcodec_alloc_context3(codec);
            if (!codec_ctx) {
                __android_log_print(ANDROID_LOG_ERROR, "VPPlayer", "Failed to allocate codec context");
                break;
            }

            if (avcodec_parameters_to_context(codec_ctx, codecpar) < 0) {
                __android_log_print(ANDROID_LOG_ERROR, "VPPlayer", "Failed to copy codec parameters to context");
                break;
            }

            if (avcodec_open2(codec_ctx, codec, nullptr) < 0) {
                __android_log_print(ANDROID_LOG_ERROR, "VPPlayer", "Failed to open codec");
                break;
            }

            frame = av_frame_alloc();
            packet = av_packet_alloc();
            if (!frame || !packet) {
                __android_log_print(ANDROID_LOG_ERROR, "VPPlayer", "Failed to allocate frame or packet");
                break;
            }

            argbBuffer.resize(codec_ctx->width * codec_ctx->height * 4);
            success = true;

            while (!mStopDecoding) {
                int ret = av_read_frame(fmt_ctx, packet);
                if (ret < 0) {
                    if (ret == AVERROR_EOF) {
                        if (av_seek_frame(fmt_ctx, videoStreamIndex, 0, AVSEEK_FLAG_BACKWARD) < 0) {
                            __android_log_print(ANDROID_LOG_ERROR, "VPPlayer", "Failed to seek to start");
                            break;
                        }
                        avcodec_flush_buffers(codec_ctx);
                        startTime = std::chrono::steady_clock::now();
                        continue;
                    } else {
                        __android_log_print(ANDROID_LOG_ERROR, "VPPlayer", "Error reading frame: %d", ret);
                        break;
                    }
                }
                if (packet->stream_index == videoStreamIndex) {
                    if (avcodec_send_packet(codec_ctx, packet) == 0) {
                        while (avcodec_receive_frame(codec_ctx, frame) == 0) {
                            if (mStopDecoding) break;
                            const char *pix_fmt_name = av_get_pix_fmt_name(static_cast<AVPixelFormat>(frame->format));
                            if (frame->format != AV_PIX_FMT_YUV420P && frame->format != AV_PIX_FMT_YUVA420P) {
                                av_frame_unref(frame);
                                continue;
                            }
                            int width = frame->width;
                            int height = frame->height;
                            int dst_stride = width * 4;
                            int64_t frameTimestampMs = getFrameTimestampMs(frame, videoStream);
                            auto expectedTime = startTime + std::chrono::milliseconds(frameTimestampMs);
                            auto now = std::chrono::steady_clock::now();
                            if (expectedTime > now) {
                                std::this_thread::sleep_until(expectedTime);
                            }
                            int conv_result = 0;
                            if (strcmp(pix_fmt_name, "yuv420p") == 0) {
                                conv_result = libyuv::I420ToABGR(frame->data[0], frame->linesize[0], frame->data[1], frame->linesize[1], frame->data[2], frame->linesize[2], argbBuffer.data(), dst_stride, width, height);
                            } else {
                                conv_result = libyuv::I420AlphaToABGR(frame->data[0], frame->linesize[0], frame->data[1], frame->linesize[1], frame->data[2], frame->linesize[2], frame->data[3], frame->linesize[3], argbBuffer.data(), dst_stride, width, height, 1);
                            }
                            if (conv_result == 0) {
                                renderFrame(argbBuffer.data(), width, height);
                            }
                            av_frame_unref(frame);
                        }
                    }
                }
                av_packet_unref(packet);
            }
        } while (false);

        av_packet_free(&packet);
        av_frame_free(&frame);
        avcodec_free_context(&codec_ctx);
        avformat_close_input(&fmt_ctx);
    }

    void renderFrame(const uint8_t* argbBuffer, int width, int height) {
        std::lock_guard<std::mutex> lock(mWindowMutex);
        if (!mWindow) {
            __android_log_print(ANDROID_LOG_WARN, "VPPlayer", "renderFrame: mWindow is null, skipping frame.");
            return;
        }
        if (ANativeWindow_setBuffersGeometry(mWindow, width, height, WINDOW_FORMAT_RGBA_8888) < 0) {
            __android_log_print(ANDROID_LOG_ERROR, "VPPlayer", "Failed to set buffer geometry for ANativeWindow %p", mWindow);
            return;
        }
        ANativeWindow_Buffer windowBuffer;
        if (ANativeWindow_lock(mWindow, &windowBuffer, nullptr) != 0) {
            __android_log_print(ANDROID_LOG_ERROR, "VPPlayer", "Failed to lock native window %p", mWindow);
            return;
        }
        for (int y = 0; y < height; y++) {
            uint8_t* dst = reinterpret_cast<uint8_t*>(windowBuffer.bits) + y * windowBuffer.stride * 4;
            const uint8_t* src = argbBuffer + y * width * 4;
            memcpy(dst, src, width * 4);
        }
        ANativeWindow_unlockAndPost(mWindow);
    }

    ANativeWindow* mWindow;
    std::string mVideoPath;
    std::atomic<bool> mStopDecoding;
    std::thread mDecodingThread;
    std::mutex mWindowMutex;
    std::atomic<bool> mThreadFinished;
};

static std::mutex g_players_mutex;
static std::map<jlong, std::shared_ptr<FFmpegPlayer>> g_players;

extern "C"
JNIEXPORT jlong JNICALL
Java_com_xxcactussell_vpplayer_VPPlayer_nativeCreate(JNIEnv *env, jobject, jstring jVideoPath, jobject jSurface) {
    const char* cVideoPath = env->GetStringUTFChars(jVideoPath, nullptr);
    std::string videoPathStr(cVideoPath);
    env->ReleaseStringUTFChars(jVideoPath, cVideoPath);
    ANativeWindow* window = ANativeWindow_fromSurface(env, jSurface);
    if (!window) {
        __android_log_print(ANDROID_LOG_ERROR, "VPPlayer", "Failed to get ANativeWindow from Surface");
        return 0;
    }

    auto player = std::make_shared<FFmpegPlayer>(window, videoPathStr);
    auto handle = reinterpret_cast<jlong>(player.get());

    std::lock_guard<std::mutex> lock(g_players_mutex);
    g_players[handle] = player;
    __android_log_print(ANDROID_LOG_DEBUG, "VPPlayer", "nativeCreate: Player created with handle %lld, ANativeWindow %p", handle, window);

    return handle;
}

extern "C"
JNIEXPORT void JNICALL
Java_com_xxcactussell_vpplayer_VPPlayer_nativeStart(JNIEnv *env, jobject, jlong handle) {
    std::lock_guard<std::mutex> lock(g_players_mutex);
    auto it = g_players.find(handle);
    if (it != g_players.end()) {
        it->second->start();
        __android_log_print(ANDROID_LOG_DEBUG, "VPPlayer", "nativeStart: Player with handle %lld started.", handle);
    } else {
        __android_log_print(ANDROID_LOG_WARN, "VPPlayer", "Start called for unknown handle: %lld", handle);
    }
}

extern "C"
JNIEXPORT void JNICALL
Java_com_xxcactussell_vpplayer_VPPlayer_nativeStop(JNIEnv *env, jobject, jlong handle) {
    std::lock_guard<std::mutex> lock(g_players_mutex);
    auto it = g_players.find(handle);
    if (it != g_players.end()) {
        it->second->stop();
        __android_log_print(ANDROID_LOG_DEBUG, "VPPlayer", "nativeStop: Player with handle %lld stopped.", handle);
    } else {
        __android_log_print(ANDROID_LOG_WARN, "VPPlayer", "Stop called for unknown handle: %lld", handle);
    }
}

extern "C"
JNIEXPORT void JNICALL
Java_com_xxcactussell_vpplayer_VPPlayer_nativeDestroy(JNIEnv *env, jobject, jlong handle) {
    std::shared_ptr<FFmpegPlayer> player_to_destroy;
    {
        std::lock_guard<std::mutex> lock(g_players_mutex);
        auto it = g_players.find(handle);
        if (it != g_players.end()) {
            player_to_destroy = it->second;
            g_players.erase(it);
            __android_log_print(ANDROID_LOG_DEBUG, "VPPlayer", "nativeDestroy: Removed player with handle %lld from map.", handle);
        } else {
            __android_log_print(ANDROID_LOG_WARN, "VPPlayer", "Destroy called for unknown handle: %lld", handle);
            return;
        }
    }
    if (player_to_destroy) {
        __android_log_print(ANDROID_LOG_DEBUG, "VPPlayer", "nativeDestroy: Calling stop() for player %lld.", handle);
        player_to_destroy->stop();
        __android_log_print(ANDROID_LOG_DEBUG, "VPPlayer", "nativeDestroy: Waiting for decoding thread to finish for player %lld.", handle);
        int attempts = 0;
        while (!player_to_destroy->getThreadFinishedFlag().load() && attempts < 100) {
            std::this_thread::sleep_for(std::chrono::milliseconds(10));
            attempts++;
        }
        if (!player_to_destroy->getThreadFinishedFlag().load()) {
            __android_log_print(ANDROID_LOG_WARN, "VPPlayer", "nativeDestroy: Decoding thread for player %lld did not finish in time, attempting to join anyway.", handle);
        }

        if (player_to_destroy->getDecodingThread().joinable()) {
            __android_log_print(ANDROID_LOG_DEBUG, "VPPlayer", "nativeDestroy: Joining decoding thread for player %lld.", handle);
            try {
                player_to_destroy->getDecodingThread().join();
                __android_log_print(ANDROID_LOG_DEBUG, "VPPlayer", "nativeDestroy: Decoding thread for player %lld joined.", handle);
            } catch (const std::system_error& e) {
                __android_log_print(ANDROID_LOG_ERROR, "VPPlayer", "nativeDestroy: thread::join failed for player %lld: %s", handle, e.what());
            }
        } else {
            __android_log_print(ANDROID_LOG_DEBUG, "VPPlayer", "nativeDestroy: Decoding thread for player %lld not joinable.", handle);
        }
    }
    __android_log_print(ANDROID_LOG_DEBUG, "VPPlayer", "nativeDestroy: Finished processing for player %lld. shared_ptr will now be destroyed.", handle);
}