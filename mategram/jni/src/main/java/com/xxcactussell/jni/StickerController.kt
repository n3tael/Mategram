package com.xxcactussell.jni

import android.graphics.Bitmap
import android.graphics.ColorSpace
import android.hardware.HardwareBuffer
import androidx.annotation.Keep
import androidx.core.graphics.createBitmap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import java.io.Closeable
import java.util.concurrent.CopyOnWriteArrayList

class StickerController(
    val type: StickerType,
    val source: Any,
    val targetWidth: Int,
    val targetHeight: Int,
    private val cacheDirPath: String? = null,
    private val sourceHash: String = "",
    private val color: Int = 0,
) : Closeable {

    enum class StickerType(val id: Int) { LOTTIE(0), WEBP(1), VP9(2) }

    @Volatile private var nativePtr: Long = 0
    @Volatile private var nativeBufferPtr: Long = 0
    private var hardwareBuffer: HardwareBuffer? = null

    @Volatile var isReleased = false
        private set

    @Volatile var hasRenderedFirstFrame = false
        private set

    val stickerWidth: Int
    val stickerHeight: Int
    val renderWidth: Int
    val renderHeight: Int
    val bitmap: Bitmap

    private var totalFrames: Int = 0
    private var frameDelayMs: Long = 16
    private var nextFrameTimeMs: Long = 0
    private var startTimeMs: Long = 0
    private var currentFrameIdx: Int = 0

    @Volatile private var newFrameAvailable = false
    private val decodingMutex = Mutex()

    private val listeners = CopyOnWriteArrayList<() -> Unit>()

    private val nativeCallback = object {
        @Keep
        fun onNativeFrameReady(delay: Int) {
            if (isReleased) return
            if (delay > 0) {
                frameDelayMs = delay.toLong().coerceIn(16, 500)
            }
            newFrameAvailable = true
            hasRenderedFirstFrame = true
            dispatchFrameReady()
        }
    }

    init {
        nativePtr = when (type) {
            StickerType.LOTTIE -> NativeStickerCore.createLottieHandle(source as String)
            StickerType.WEBP -> NativeStickerCore.createWebPHandleFromBytes(source as ByteArray)
            StickerType.VP9 -> NativeStickerCore.createVpxHandleFromBytes(source as ByteArray)
        }

        val dims = IntArray(2)
        when (type) {
            StickerType.LOTTIE -> NativeStickerCore.getLottieSize(nativePtr, dims)
            StickerType.WEBP -> NativeStickerCore.getWebPSize(nativePtr, dims)
            StickerType.VP9 -> NativeStickerCore.getVpxSize(nativePtr, dims)
        }
        stickerWidth = dims[0].coerceAtLeast(1)
        stickerHeight = dims[1].coerceAtLeast(1)

        val scale = com.xxcactussell.utils.PerformanceProfile.getResolutionScale(targetWidth < 150)
        renderWidth = (targetWidth * scale).toInt().coerceAtLeast(1)
        renderHeight = (targetHeight * scale).toInt().coerceAtLeast(1)

        when (type) {
            StickerType.LOTTIE -> NativeStickerCore.prepareLottieRendering(nativePtr, renderWidth, renderHeight)
            StickerType.WEBP -> NativeStickerCore.prepareWebPRendering(nativePtr, renderWidth, renderHeight)
            StickerType.VP9 -> NativeStickerCore.prepareVpxRendering(nativePtr, renderWidth, renderHeight)
        }

        var createdBitmap: Bitmap? = null
        val ptrOut = LongArray(1)
        val hwBuf = NativeStickerCore.acquireNativeBuffer(renderWidth, renderHeight, ptrOut)
        if (hwBuf != null) {
            hardwareBuffer = hwBuf
            nativeBufferPtr = ptrOut[0]
            createdBitmap = Bitmap.wrapHardwareBuffer(hwBuf, ColorSpace.get(ColorSpace.Named.SRGB))
        }

        bitmap = createdBitmap ?: createBitmap(renderWidth, renderHeight)

        if (type == StickerType.LOTTIE) {
            totalFrames = NativeStickerCore.getLottieFrameCount(nativePtr).coerceAtLeast(1)
            val fps = NativeStickerCore.getLottieFrameRate(nativePtr).toInt().coerceIn(1, 60)
            frameDelayMs = 1000L / fps
        }
    }

    fun tryDecodeNextFrame(currentTimeMs: Long, scope: CoroutineScope) {
        if (isReleased || decodingMutex.isLocked || currentTimeMs < nextFrameTimeMs) return

        if (decodingMutex.tryLock()) {
            scope.launch {
                try {
                    if (!isReleased) {
                        doUpdateFrame(currentTimeMs)
                        nextFrameTimeMs = currentTimeMs + frameDelayMs
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    decodingMutex.unlock()
                }
            }
        }
    }

    private fun doUpdateFrame(currentTimeMs: Long) {
        val ptr = nativePtr
        val bufPtr = nativeBufferPtr
        if (isReleased || ptr == 0L || bufPtr == 0L) return

        if (startTimeMs == 0L) startTimeMs = currentTimeMs

        val frameIdx = if (type == StickerType.LOTTIE) {
            ((currentTimeMs - startTimeMs) / frameDelayMs % totalFrames).toInt()
        } else {
            currentFrameIdx++
        }

        val colorSuffix = if (color != 0) "_c$color" else ""
        val cachePath = if (cacheDirPath != null) {
            "$cacheDirPath/${sourceHash}_${renderWidth}x${renderHeight}_$frameIdx$colorSuffix.zstd"
        } else ""

        NativeStickerCore.renderAsync(type.id, ptr, bufPtr, frameIdx, color, cachePath, nativeCallback)
    }

    fun checkNewFrameAvailable(): Boolean {
        if (newFrameAvailable) {
            newFrameAvailable = false
            return true
        }
        return false
    }

    fun addFrameListener(listener: () -> Unit) {
        listeners.addIfAbsent(listener)
    }

    fun removeFrameListener(listener: () -> Unit) {
        listeners.remove(listener)
    }

    fun dispatchFrameReady() {
        for (listener in listeners) {
            listener.invoke()
        }
    }

    override fun close() {
        if (isReleased) return
        isReleased = true

        listeners.clear()
        StickerAnimScheduler.remove(this)

        val ptr = nativePtr
        if (ptr != 0L) {
            nativePtr = 0L
            when (type) {
                StickerType.LOTTIE -> NativeStickerCore.destroyLottieHandle(ptr)
                StickerType.WEBP -> NativeStickerCore.destroyWebPHandle(ptr)
                StickerType.VP9 -> NativeStickerCore.destroyVpxHandle(ptr)
            }
        }

        hardwareBuffer?.close()
        hardwareBuffer = null

        val bufPtr = nativeBufferPtr
        if (bufPtr != 0L) {
            nativeBufferPtr = 0L
            NativeStickerCore.releaseNativeBuffer(bufPtr)
        }
    }
}