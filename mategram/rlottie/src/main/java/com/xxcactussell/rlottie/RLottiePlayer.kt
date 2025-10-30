package com.xxcactussell.rlottie

import android.graphics.Bitmap

object RLottiePlayer {
    init {
        try {
            System.loadLibrary("mategram-native")
        } catch (e: UnsatisfiedLinkError) {
            e.printStackTrace()
        }
    }

    external fun nativeCreateFromData(data: ByteArray, cacheKey: String): Long
    external fun nativeDestroy(nativePtr: Long)
    external fun nativeGetFrameCount(nativePtr: Long): Int
    external fun nativeGetFrameRate(nativePtr: Long): Double
    external fun nativeRenderFrame(nativePtr: Long, frame: Int, bitmap: Bitmap, width: Int, height: Int)
}