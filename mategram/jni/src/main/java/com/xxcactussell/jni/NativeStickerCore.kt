package com.xxcactussell.jni

import android.graphics.Bitmap
import android.util.Log
import java.nio.ByteBuffer

object NativeStickerCore {
    init {
        try {
            System.loadLibrary("mgjni")
        } catch (e: UnsatisfiedLinkError) {
            Log.e("MategramNative", "Failed to load native library", e)
        }
    }

    // rLottie
    external fun createLottieHandle(json: String): Long
    external fun destroyLottieHandle(ptr: Long)
    external fun getLottieFrameCount(ptr: Long): Int
    external fun getLottieFrameRate(ptr: Long): Float // Added: Get Lottie frame rate

    // WebP
    external fun createWebPHandle(data: ByteBuffer): Long // Changed from ByteArray to ByteBuffer
    external fun destroyWebPHandle(ptr: Long)

    // VP9
    external fun createVpxHandle(data: ByteBuffer): Long
    external fun destroyVpxHandle(ptr: Long)
    external fun seekVpxToStart(ptr: Long)

    // Size getters
    external fun getLottieSize(ptr: Long, outArray: IntArray)
    external fun getWebPSize(ptr: Long, outArray: IntArray)
    external fun getVpxSize(ptr: Long, outArray: IntArray)

    // Render preparation
    external fun prepareLottieRendering(ptr: Long, width: Int, height: Int)
    external fun prepareWebPRendering(ptr: Long, width: Int, height: Int)
    external fun prepareVpxRendering(ptr: Long, width: Int, height: Int)

    external fun setCacheDir(path: String)
    external fun shutdownCache()

    external fun renderLottieWithCache(ptr: Long, frame: Int, bitmap: Bitmap)
    external fun renderWebPWithCache(ptr: Long, bitmap: Bitmap): Int
    external fun renderVpxWithCache(ptr: Long, bitmap: Bitmap): Int
}