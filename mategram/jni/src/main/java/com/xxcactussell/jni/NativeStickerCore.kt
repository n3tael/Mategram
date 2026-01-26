package com.xxcactussell.jni

import android.graphics.Bitmap
import android.hardware.HardwareBuffer
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
    external fun getLottieFrameRate(ptr: Long): Float

    external fun renderLottieDirect(ptr: Long, frame: Int, bufferPtr: Long) : Int

    // WebP
    external fun createWebPHandle(data: ByteBuffer): Long
    external fun createWebPHandleFromBytes(data: ByteArray): Long
    external fun destroyWebPHandle(ptr: Long)

    external fun renderWebPDirect(ptr: Long, bufferPtr: Long) : Int

    // VP9
    external fun createVpxHandle(data: ByteBuffer): Long
    external fun destroyVpxHandle(ptr: Long)
    external fun createVpxHandleFromBytes(data: ByteArray): Long
    external fun seekVpxToStart(ptr: Long)

    external fun renderVpxDirect(playerPtr: Long, bufferPtr: Long) : Int

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

    //buffer
    external fun releaseNativeBuffer(ptr: Long)
    external fun acquireNativeBuffer(w: Int, h: Int, outPtr: LongArray): HardwareBuffer?

    external fun renderAsync(type: Int, ptr: Long, bufferPtr: Long, frame: Int, cachePath: String, listener: Any)

    external fun renderLottieWithCache(ptr: Long, frame: Int, bitmap: Bitmap)
    external fun renderWebPWithCache(ptr: Long, bitmap: Bitmap): Int
    external fun renderVpxWithCache(ptr: Long, bitmap: Bitmap): Int
}