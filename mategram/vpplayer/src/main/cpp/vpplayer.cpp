#include <jni.h>
#include <android/bitmap.h>
#include <string>
#include <chrono>

extern "C" {
#include "libffmpeg-prebuilt/include/libavformat/avformat.h"
#include "libffmpeg-prebuilt/include/libavcodec/avcodec.h"
#include "libffmpeg-prebuilt/include/libavutil/imgutils.h"
}

#include "libyuv-prebuilt/include/libyuv.h"

class VPPlayer {
public:
    VPPlayer(std::string path) : mFilePath(std::move(path)) {}
    ~VPPlayer() { release(); }

    bool init() {
        if (avformat_open_input(&fmt_ctx, mFilePath.c_str(), nullptr, nullptr) != 0) return false;
        if (avformat_find_stream_info(fmt_ctx, nullptr) < 0) return false;

        for (unsigned int i = 0; i < fmt_ctx->nb_streams; ++i) {
            if (fmt_ctx->streams[i]->codecpar->codec_type == AVMEDIA_TYPE_VIDEO) {
                videoStreamIndex = (int)i;
                break;
            }
        }
        if (videoStreamIndex == -1) return false;

        AVCodecParameters* codecpar = fmt_ctx->streams[videoStreamIndex]->codecpar;
        const AVCodec* codec = avcodec_find_decoder(codecpar->codec_id);
        if (!codec) return false;

        codec_ctx = avcodec_alloc_context3(codec);
        avcodec_parameters_to_context(codec_ctx, codecpar);

        codec_ctx->thread_count = 4;
        codec_ctx->thread_type = FF_THREAD_FRAME;

        if (avcodec_open2(codec_ctx, codec, nullptr) < 0) return false;

        packet = av_packet_alloc();
        frame = av_frame_alloc();
        mStartTime = std::chrono::steady_clock::now();
        return true;
    }

    long renderNextFrame(JNIEnv* env, jobject jBitmap) {
        if (!codec_ctx) return -1;

        while (true) {
            int ret = av_read_frame(fmt_ctx, packet);
            if (ret < 0) {
                av_packet_unref(packet);
                return -1; // EOF
            }

            if (packet->stream_index == videoStreamIndex) {
                if (avcodec_send_packet(codec_ctx, packet) == 0) {
                    if (avcodec_receive_frame(codec_ctx, frame) == 0) {

                        void* pixels;
                        AndroidBitmapInfo info;

                        if (AndroidBitmap_lockPixels(env, jBitmap, &pixels) >= 0) {
                            AndroidBitmap_getInfo(env, jBitmap, &info);

                            if (frame->format == AV_PIX_FMT_YUVA420P) {
                                libyuv::I420AlphaToABGR(
                                        frame->data[0], frame->linesize[0],
                                        frame->data[1], frame->linesize[1],
                                        frame->data[2], frame->linesize[2],
                                        frame->data[3], frame->linesize[3],
                                        (uint8_t*)pixels, info.stride,
                                        frame->width, frame->height,
                                        1
                                );
                            } else {
                                libyuv::I420ToABGR(
                                        frame->data[0], frame->linesize[0],
                                        frame->data[1], frame->linesize[1],
                                        frame->data[2], frame->linesize[2],
                                        (uint8_t*)pixels, info.stride,
                                        frame->width, frame->height
                                );
                            }
                            AndroidBitmap_unlockPixels(env, jBitmap);
                        }
                        int64_t ptsMs = av_rescale_q(frame->pts, fmt_ctx->streams[videoStreamIndex]->time_base, {1, 1000});
                        auto now = std::chrono::steady_clock::now();
                        auto elapsed = std::chrono::duration_cast<std::chrono::milliseconds>(now - mStartTime).count();
                        long diff = (long)(ptsMs - elapsed);

                        av_frame_unref(frame);
                        av_packet_unref(packet);
                        return (diff > 0) ? diff : 0;
                    }
                }
            }
            av_packet_unref(packet);
        }
    }

    void seekToStart() {
        if (fmt_ctx) {
            av_seek_frame(fmt_ctx, videoStreamIndex, 0, AVSEEK_FLAG_BACKWARD);
            if (codec_ctx) avcodec_flush_buffers(codec_ctx);
            mStartTime = std::chrono::steady_clock::now();
        }
    }

    void getVideoSize(int* w, int* h) {
        if (codec_ctx) {
            *w = codec_ctx->width;
            *h = codec_ctx->height;
        }
    }

    void release() {
        if (frame) av_frame_free(&frame);
        if (packet) av_packet_free(&packet);
        if (codec_ctx) avcodec_free_context(&codec_ctx);
        if (fmt_ctx) avformat_close_input(&fmt_ctx);
    }

private:
    std::string mFilePath;
    AVFormatContext* fmt_ctx = nullptr;
    AVCodecContext* codec_ctx = nullptr;
    int videoStreamIndex = -1;
    AVFrame* frame = nullptr;
    AVPacket* packet = nullptr;
    std::chrono::steady_clock::time_point mStartTime;
};

extern "C" JNIEXPORT jlong JNICALL
Java_com_xxcactussell_vpplayer_VPPlayer_nativeCreate(JNIEnv *env, jobject, jstring jPath) {
    const char* path = env->GetStringUTFChars(jPath, nullptr);
    auto* player = new VPPlayer(path);
    env->ReleaseStringUTFChars(jPath, path);
    if (!player->init()) { delete player; return 0; }
    return reinterpret_cast<jlong>(player);
}

extern "C" JNIEXPORT jlong JNICALL
Java_com_xxcactussell_vpplayer_VPPlayer_nativeRenderNextFrame(JNIEnv *env, jobject, jlong handle, jobject jBitmap) {
    auto* player = reinterpret_cast<VPPlayer*>(handle);
    return player ? player->renderNextFrame(env, jBitmap) : -1;
}

extern "C" JNIEXPORT void JNICALL
Java_com_xxcactussell_vpplayer_VPPlayer_nativeSeekToStart(JNIEnv *env, jobject, jlong handle) {
    auto* player = reinterpret_cast<VPPlayer*>(handle);
    if (player) player->seekToStart();
}

extern "C" JNIEXPORT void JNICALL
Java_com_xxcactussell_vpplayer_VPPlayer_nativeDestroy(JNIEnv *env, jobject, jlong handle) {
    auto* player = reinterpret_cast<VPPlayer*>(handle);
    if (player) delete player;
}

extern "C" JNIEXPORT void JNICALL
Java_com_xxcactussell_vpplayer_VPPlayer_nativeGetVideoSize(JNIEnv *env, jobject, jlong handle, jintArray dimens) {
    auto* player = reinterpret_cast<VPPlayer*>(handle);
    if (player) {
        jint* arr = env->GetIntArrayElements(dimens, nullptr);
        int w=0, h=0;
        player->getVideoSize(&w, &h);
        arr[0] = w; arr[1] = h;
        env->ReleaseIntArrayElements(dimens, arr, 0);
    }
}