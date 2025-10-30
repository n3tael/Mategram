#include <jni.h>
#include <string>
#include "rlottie/inc/rlottie.h"
#include <android/bitmap.h>
#include <android/log.h>

#define LOG_TAG "RLottieJNI"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)

extern "C"
JNIEXPORT jlong JNICALL
Java_com_xxcactussell_rlottie_RLottiePlayer_nativeCreateFromData(JNIEnv *env, jobject, jbyteArray jdata, jstring jcacheKey) {
    const char *cacheKey = env->GetStringUTFChars(jcacheKey, 0);
    jbyte *data = env->GetByteArrayElements(jdata, nullptr);
    jsize len = env->GetArrayLength(jdata);

    LOGI("nativeCreateFromData called with data size: %d, cacheKey: %s", len, cacheKey);
    std::string data_str(reinterpret_cast<char*>(data), len);
    auto animation = rlottie::Animation::loadFromData(std::move(data_str), cacheKey);

    env->ReleaseByteArrayElements(jdata, data, JNI_ABORT);
    env->ReleaseStringUTFChars(jcacheKey, cacheKey);

    if (!animation) {
        LOGE("Failed to load animation from data.");
        return 0;
    }

    auto ptr = animation.release();
    LOGI("Animation loaded successfully from data. Pointer: %ld", (long)ptr);
    return reinterpret_cast<jlong>(ptr);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_xxcactussell_rlottie_RLottiePlayer_nativeDestroy(JNIEnv *env, jobject, jlong native_ptr) {
    if (native_ptr == 0) return;
    LOGI("nativeDestroy called for pointer: %ld", (long)native_ptr);
    delete reinterpret_cast<rlottie::Animation *>(native_ptr);
}

extern "C"
JNIEXPORT int JNICALL
Java_com_xxcactussell_rlottie_RLottiePlayer_nativeGetFrameCount(JNIEnv *env, jobject, jlong native_ptr) {
    if (native_ptr == 0) return 0;
    auto *animation = reinterpret_cast<rlottie::Animation *>(native_ptr);
    size_t totalFrame = animation->totalFrame();
    LOGI("nativeGetFrameCount: %zu", totalFrame);
    return static_cast<jint>(totalFrame);
}

extern "C"
JNIEXPORT double JNICALL
Java_com_xxcactussell_rlottie_RLottiePlayer_nativeGetFrameRate(JNIEnv *env, jobject, jlong native_ptr) {
    if (native_ptr == 0) return 0;
    auto *animation = reinterpret_cast<rlottie::Animation *>(native_ptr);
    return static_cast<jdouble>(animation->frameRate());
}

extern "C"
JNIEXPORT void JNICALL
Java_com_xxcactussell_rlottie_RLottiePlayer_nativeRenderFrame(JNIEnv *env, jobject, jlong native_ptr, jint frame, jobject bitmap, jint width, jint height) {
    if (native_ptr == 0) return;
    auto *animation = reinterpret_cast<rlottie::Animation *>(native_ptr);

    void *pixels;
    if (AndroidBitmap_lockPixels(env, bitmap, &pixels) < 0) {
        LOGE("Failed to lock pixels");
        return;
    }
    rlottie::Surface surface(static_cast<uint32_t *>(pixels), width, height, width * 4);
    animation->renderSync(frame, surface);
    auto *pixel_data = static_cast<uint32_t *>(pixels);
    int total_pixels = width * height;
    for (int i = 0; i < total_pixels; ++i) {
        uint32_t pixel = pixel_data[i];
        uint32_t alpha = pixel & 0xFF000000;
        uint32_t red   = pixel & 0x00FF0000;
        uint32_t green = pixel & 0x0000FF00;
        uint32_t blue  = pixel & 0x000000FF;
        pixel_data[i] = alpha | (blue << 16) | green | (red >> 16);
    }
    AndroidBitmap_unlockPixels(env, bitmap);
}