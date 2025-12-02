package com.xxcactussell.presentation.tools

import android.graphics.Bitmap
import android.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.createBitmap
import com.xxcactussell.rlottie.RLottiePlayer
import com.xxcactussell.vpplayer.VPPlayer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.isActive
import kotlinx.coroutines.yield
import java.io.File
import java.util.concurrent.ConcurrentHashMap
import java.util.zip.GZIPInputStream

private fun decompressGzipIfNeeded(data: ByteArray): ByteArray {
    return if (data.size >= 2 && data[0] == 0x1f.toByte() && data[1] == 0x8b.toByte()) {
        try {
            data.inputStream().use { input ->
                GZIPInputStream(input).use { gzip ->
                    gzip.readBytes()
                }
            }
        } catch (e: Exception) {
            data
        }
    } else {
        data
    }
}

fun getMaxRenderSize(size: Dp): Int {
    return if (size > 100.dp) {
        256
    } else if (size > 50.dp) {
        128
    } else {
        64
    }
}

private val RENDER_DISPATCHER = Dispatchers.IO

object SharedWebMEngine {
    private val scope = CoroutineScope(RENDER_DISPATCHER + SupervisorJob())
    private val activeFlows = ConcurrentHashMap<String, SharedFlow<ImageBitmap?>>()

    fun getFlowFor(path: String): Flow<ImageBitmap?> {
        val key = "$path@original"
        return activeFlows.computeIfAbsent(key) {
            createWebMRenderFlow(path)
                .flowOn(RENDER_DISPATCHER)
                .shareIn(
                    scope,
                    started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 1000),
                    replay = 1
                )
        }
    }

    private fun createWebMRenderFlow(path: String): Flow<ImageBitmap?> = flow {
        var nativePtr = 0L

        try {
            if (!File(path).exists()) { emit(null); return@flow }

            nativePtr = VPPlayer.nativeCreate(path)
            if (nativePtr == 0L) { emit(null); return@flow }

            val sizes = IntArray(2)
            VPPlayer.nativeGetVideoSize(nativePtr, sizes)
            val width = sizes[0]
            val height = sizes[1]

            if (width <= 0 || height <= 0) { emit(null); return@flow }

            val bufferA = createBitmap(width, height, Bitmap.Config.ARGB_8888)
            val bufferB = createBitmap(width, height, Bitmap.Config.ARGB_8888)

            var useBufferA = true

            while (currentCoroutineContext().isActive) {
                val targetBitmap = if (useBufferA) bufferA else bufferB
                val delayMs = VPPlayer.nativeRenderNextFrame(nativePtr, targetBitmap)

                if (delayMs >= 0) {
                    val emitted = targetBitmap.copy(Bitmap.Config.ARGB_8888, false)
                    emit(emitted.asImageBitmap())

                    useBufferA = !useBufferA
                    if (delayMs > 0) delay(delayMs) else yield()
                } else {
                    VPPlayer.nativeSeekToStart(nativePtr)
                    yield()
                }
            }

        } catch (t: Throwable) {
            t.printStackTrace()
        } finally {
            if (nativePtr != 0L) VPPlayer.nativeDestroy(nativePtr)
        }
    }
}

object SharedStickerEngine {
    private val scope = CoroutineScope(RENDER_DISPATCHER + SupervisorJob())
    private val activeFlows = ConcurrentHashMap<String, SharedFlow<ImageBitmap?>>()

    fun getFlowFor(path: String, size: Int): Flow<ImageBitmap?> {
        val key = "$path@$size"
        return activeFlows.computeIfAbsent(key) {
            createRenderFlow(path, size)
                .flowOn(RENDER_DISPATCHER)
                .shareIn(
                    scope,
                    started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 1000),
                    replay = 1
                )
        }
    }

    private fun createRenderFlow(path: String, maxSize: Int): Flow<ImageBitmap?> = flow {
        var nativePtr = 0L
        val buffers = arrayOfNulls<Bitmap>(2)

        try {
            val file = File(path)
            if (!file.exists()) { emit(null); return@flow }

            val compressedData = file.readBytes()
            val decompressedData = decompressGzipIfNeeded(compressedData)

            nativePtr = RLottiePlayer.nativeCreateFromData(decompressedData, path)
            if (nativePtr == 0L) { emit(null); return@flow }

            val totalFrames = RLottiePlayer.nativeGetFrameCount(nativePtr)
            var frameRate = RLottiePlayer.nativeGetFrameRate(nativePtr)
            if (frameRate <= 0.0) frameRate = 60.0
            val frameDurationNanos = (1_000_000_000.0 / frameRate).toLong()

            buffers[0] = createBitmap(maxSize, maxSize, Bitmap.Config.ARGB_8888)
            buffers[1] = createBitmap(maxSize, maxSize, Bitmap.Config.ARGB_8888)

            var bufferIndex = 0
            var lastFrameIndex = -1
            val startTime = System.nanoTime()

            while (currentCoroutineContext().isActive) {
                val now = System.nanoTime()
                val elapsedNanos = now - startTime

                if (totalFrames > 0) {
                    val totalDurationNanos = totalFrames * frameDurationNanos
                    val timeInCycle = elapsedNanos % totalDurationNanos
                    val targetFrame = (timeInCycle / frameDurationNanos).toInt()

                    if (targetFrame != lastFrameIndex) {
                        val currentBitmap = buffers[bufferIndex]!!
                        currentBitmap.eraseColor(Color.TRANSPARENT)

                        RLottiePlayer.nativeRenderFrame(
                            nativePtr,
                            targetFrame,
                            currentBitmap,
                            maxSize,
                            maxSize
                        )

                        val emitted = currentBitmap.copy(Bitmap.Config.ARGB_8888, false)
                        emit(emitted.asImageBitmap())

                        bufferIndex = 1 - bufferIndex
                        lastFrameIndex = targetFrame
                    }
                }

                val nextFrameTime = startTime + ((elapsedNanos / frameDurationNanos) + 1) * frameDurationNanos
                val delayNanos = nextFrameTime - System.nanoTime()
                if (delayNanos > 0) delay(delayNanos / 1_000_000) else yield()
            }

        } catch (t: Throwable) {
            t.printStackTrace()
        } finally {
            if (nativePtr != 0L) RLottiePlayer.nativeDestroy(nativePtr)
        }
    }
}