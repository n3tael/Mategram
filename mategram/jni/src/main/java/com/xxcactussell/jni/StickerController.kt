package com.xxcactussell.jni

import android.graphics.Bitmap
import com.xxcactussell.utils.PerformanceProfile
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.io.Closeable

class StickerController(
    val type: StickerType,
    source: Any,
    targetWidth: Int,
    targetHeight: Int,
    cacheDirPath: String? = null
) : Closeable {

    enum class StickerType { LOTTIE, WEBP, VP9 }

    @Volatile
    private var nativePtr: Long = 0

    @Volatile
    var isReleased = false
        private set

    val stickerWidth: Int
    val stickerHeight: Int
    val renderWidth: Int
    val renderHeight: Int

    private var totalFrames: Int = 0
    private var currentFrame: Int = 0
    private var nextFrameTimeMs: Long = 0
    private var frameDelayMs: Long = 16

    private var startTimeMs: Long = 0

    val bitmap: Bitmap

    @Volatile
    private var newFrameAvailable = false

    private val decodingMutex = Mutex()
    var onFrameReady: (() -> Unit)? = null

    init {
        cacheDirPath?.let { NativeStickerCore.setCacheDir(it) }

        nativePtr = when (type) {
            StickerType.LOTTIE -> if (source is String) NativeStickerCore.createLottieHandle(source) else 0L
            StickerType.WEBP -> if (source is ByteArray) NativeStickerCore.createWebPHandleFromBytes(source) else 0L
            StickerType.VP9 -> if (source is ByteArray) NativeStickerCore.createVpxHandleFromBytes(source) else 0L
        }

        val dims = IntArray(2)
        if (nativePtr != 0L) {
            when (type) {
                StickerType.LOTTIE -> NativeStickerCore.getLottieSize(nativePtr, dims)
                StickerType.WEBP -> NativeStickerCore.getWebPSize(nativePtr, dims)
                StickerType.VP9 -> NativeStickerCore.getVpxSize(nativePtr, dims)
            }
        }

        stickerWidth = dims[0].takeIf { it > 0 } ?: 1
        stickerHeight = dims[1].takeIf { it > 0 } ?: 1

        val isEmoji = targetWidth < 150
        val scale = if (isEmoji && targetWidth < 100) 1.0f else PerformanceProfile.getResolutionScale(isEmoji)

        renderWidth = (targetWidth * scale).toInt().coerceAtLeast(1)
        renderHeight = (targetHeight * scale).toInt().coerceAtLeast(1)

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
            val fps = NativeStickerCore.getLottieFrameRate(nativePtr).toInt()
            frameDelayMs = (1000L / fps.coerceAtMost(PerformanceProfile.targetFps))
        }
    }

    fun tryDecodeNextFrame(currentTimeMs: Long, scope: CoroutineScope) {
        if (currentTimeMs < nextFrameTimeMs || isReleased || decodingMutex.isLocked) return

        if (currentTimeMs - nextFrameTimeMs > 500) {
            nextFrameTimeMs = currentTimeMs
        }

        if (decodingMutex.tryLock()) {
            scope.launch {
                try {
                    val delay = doUpdateFrame(currentTimeMs)
                    if (delay >= 0) {
                        nextFrameTimeMs = currentTimeMs + delay
                        newFrameAvailable = true
                    }
                } finally {
                    decodingMutex.unlock()
                }
            }
        }
    }

    fun checkNewFrameAvailable(): Boolean {
        if (newFrameAvailable) {
            newFrameAvailable = false
            return true
        }
        return false
    }

    private fun doUpdateFrame(currentTimeMs: Long): Int {
        val ptr = nativePtr
        if (ptr == 0L || isReleased) return -1

        if (startTimeMs == 0L) startTimeMs = currentTimeMs

        return when (type) {
            StickerType.LOTTIE -> {
                val elapsed = currentTimeMs - startTimeMs
                val frameIndex = ((elapsed / frameDelayMs) % totalFrames).toInt()

                NativeStickerCore.renderLottieWithCache(ptr, frameIndex, bitmap)
                frameDelayMs.toInt()
            }
            StickerType.WEBP -> NativeStickerCore.renderWebPWithCache(ptr, bitmap)
            StickerType.VP9 -> {
                var delay = NativeStickerCore.renderVpxWithCache(ptr, bitmap)
                if (delay < 0) {
                    NativeStickerCore.seekVpxToStart(ptr)
                    delay = NativeStickerCore.renderVpxWithCache(ptr, bitmap)
                }
                delay.toLong().coerceAtLeast(PerformanceProfile.minFrameDelay).toInt()
            }
        }
    }

    override fun close() {
        isReleased = true
        StickerAnimScheduler.remove(this)

        runBlocking {
            decodingMutex.withLock {
                if (nativePtr != 0L) {
                    when (type) {
                        StickerType.LOTTIE -> NativeStickerCore.destroyLottieHandle(nativePtr)
                        StickerType.WEBP -> NativeStickerCore.destroyWebPHandle(nativePtr)
                        StickerType.VP9 -> NativeStickerCore.destroyVpxHandle(nativePtr)
                    }
                    nativePtr = 0L
                }
            }
        }
        BitmapProvider.release(bitmap)
    }
}