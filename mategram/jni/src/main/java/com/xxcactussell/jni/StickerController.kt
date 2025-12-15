package com.xxcactussell.jni

import android.graphics.Bitmap
import java.io.Closeable
import java.nio.ByteBuffer
import kotlin.math.min

class StickerController(
    val type: StickerType,
    source: Any,
    targetWidth: Int,
    targetHeight: Int,
    cacheDirPath: String? = null
) : Closeable {

    init { NativeStickerCore }

    private val lock = Any()

    enum class StickerType { LOTTIE, WEBP, VP9 }

    @Volatile
    private var nativePtr: Long = 0
    val stickerWidth: Int
    val stickerHeight: Int
    val renderWidth: Int
    val renderHeight: Int
    private var totalFrames: Int = 0
    private var currentFrame: Int = 0
    private var nextFrameDelay: Long = 1000L / 60L
    private var directByteBuffer: ByteBuffer? = null

    val bitmap: Bitmap

    init {
        cacheDirPath?.let { NativeStickerCore.setCacheDir(it) }

        when (type) {
            StickerType.LOTTIE -> {
                if (source is String) {
                    nativePtr = NativeStickerCore.createLottieHandle(source)
                }
            }
            StickerType.WEBP -> {
                if (source is ByteArray) {
                    directByteBuffer = ByteBuffer.allocateDirect(source.size)
                    directByteBuffer?.put(source)
                    directByteBuffer?.flip()
                    nativePtr = NativeStickerCore.createWebPHandle(directByteBuffer!!)
                }
            }
            StickerType.VP9 -> {
                if (source is ByteArray) {
                    directByteBuffer = ByteBuffer.allocateDirect(source.size)
                    directByteBuffer?.put(source)
                    directByteBuffer?.flip()
                    nativePtr = NativeStickerCore.createVpxHandle(directByteBuffer!!)
                }
            }
        }

        val dimensions = IntArray(2)
        if (nativePtr != 0L) {
            when (type) {
                StickerType.LOTTIE -> NativeStickerCore.getLottieSize(nativePtr, dimensions)
                StickerType.WEBP -> NativeStickerCore.getWebPSize(nativePtr, dimensions)
                StickerType.VP9 -> NativeStickerCore.getVpxSize(nativePtr, dimensions)
            }
        }

        stickerWidth = dimensions[0].takeIf { it > 0 } ?: 1
        stickerHeight = dimensions[1].takeIf { it > 0 } ?: 1

        val widthRatio = targetWidth.toFloat() / stickerWidth
        val heightRatio = targetHeight.toFloat() / stickerHeight
        val ratio = min(widthRatio, heightRatio)

        renderWidth = (stickerWidth * ratio).toInt().coerceAtLeast(1)
        renderHeight = (stickerHeight * ratio).toInt().coerceAtLeast(1)

        if (nativePtr != 0L) {
            when (type) {
                StickerType.LOTTIE -> NativeStickerCore.prepareLottieRendering(nativePtr, renderWidth, renderHeight)
                StickerType.WEBP -> NativeStickerCore.prepareWebPRendering(nativePtr, renderWidth, renderHeight)
                StickerType.VP9 -> NativeStickerCore.prepareVpxRendering(nativePtr, renderWidth, renderHeight)
            }
        }

        bitmap = BitmapProvider.acquire(renderWidth, renderHeight, Bitmap.Config.ARGB_8888)

        if (type == StickerType.LOTTIE && nativePtr != 0L) {
            totalFrames = NativeStickerCore.getLottieFrameCount(nativePtr)
            val frameRate = NativeStickerCore.getLottieFrameRate(nativePtr)
            if (frameRate > 0) {
                nextFrameDelay = (1000L / frameRate).toLong().coerceAtLeast(1L)
            }
        }
    }

    fun updateFrame() {
        synchronized(lock) {
            if (nativePtr == 0L) return

            when (type) {
                StickerType.LOTTIE -> {
                    NativeStickerCore.renderLottieWithCache(nativePtr, currentFrame, bitmap)
                    currentFrame = (currentFrame + 1) % (if (totalFrames > 0) totalFrames else 1)
                }
                StickerType.WEBP -> {
                    val duration = NativeStickerCore.renderWebPWithCache(nativePtr, bitmap)
                    nextFrameDelay = if (duration > 0) duration.toLong().coerceAtLeast(1L) else 40L
                }
                StickerType.VP9 -> {
                    var duration = NativeStickerCore.renderVpxWithCache(nativePtr, bitmap)
                    if (duration < 0) {
                        NativeStickerCore.seekVpxToStart(nativePtr)
                        duration = NativeStickerCore.renderVpxWithCache(nativePtr, bitmap)
                    }
                    nextFrameDelay = if (duration >= 0) duration.toLong().coerceAtLeast(1L) else 40L
                }
            }
        }
    }

    fun getNextFrameDelay(): Long = nextFrameDelay

    override fun close() {
        synchronized(lock) {
            if (nativePtr != 0L) {
                when (type) {
                    StickerType.LOTTIE -> NativeStickerCore.destroyLottieHandle(nativePtr)
                    StickerType.WEBP -> NativeStickerCore.destroyWebPHandle(nativePtr)
                    StickerType.VP9 -> NativeStickerCore.destroyVpxHandle(nativePtr)
                }
                nativePtr = 0L
            }
        }
        BitmapProvider.release(bitmap)
        directByteBuffer = null
    }
}
