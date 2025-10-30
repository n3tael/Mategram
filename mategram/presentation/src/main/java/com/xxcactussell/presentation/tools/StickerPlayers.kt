package com.xxcactussell.presentation.tools

import android.graphics.Bitmap
import android.graphics.SurfaceTexture
import android.view.Surface
import android.view.TextureView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.graphics.createBitmap
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.xxcactussell.rlottie.RLottiePlayer
import com.xxcactussell.vpplayer.VPPlayer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.withContext
import java.io.ByteArrayInputStream
import java.io.File
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.locks.ReentrantLock
import java.util.zip.GZIPInputStream
import kotlin.math.roundToInt

private fun decompressGzip(data: ByteArray): ByteArray {
    return GZIPInputStream(ByteArrayInputStream(data)).use { it.readBytes() }
}

private const val MAX_RENDER_SIZE_PX = 256

@Composable
fun LottieSticker(
    path: String,
    size: Dp,
    modifier: Modifier = Modifier
) {
    val uniqueCacheKey = remember { path + "::" + java.util.UUID.randomUUID().toString() }

    val nativeInfo = remember(path, uniqueCacheKey) {
        try {
            val file = File(path)
            if (!file.exists()) return@remember null
            val compressedData = file.readBytes()
            val decompressedData = decompressGzip(compressedData)
            val ptr = RLottiePlayer.nativeCreateFromData(decompressedData, uniqueCacheKey)
            if (ptr != 0L) {
                Triple(ptr, RLottiePlayer.nativeGetFrameCount(ptr), RLottiePlayer.nativeGetFrameRate(ptr))
            } else {
                null
            }
        } catch (e: Throwable) {
            null
        }
    }

    if (nativeInfo == null) {
        Box(modifier = modifier.size(size))
        return
    }

    val (nativePtr, frameCount, frameRate) = nativeInfo

    val isRenderingActive = remember { AtomicBoolean(true) }
    val nativeLock = remember { ReentrantLock() }

    DisposableEffect(nativePtr) {
        onDispose {
            isRenderingActive.set(false)
            nativeLock.lock()
            try {
                if (nativePtr != 0L) {
                    RLottiePlayer.nativeDestroy(nativePtr)
                }
            } finally {
                nativeLock.unlock()
            }
        }
    }

    var intSize by remember { mutableStateOf(IntSize.Zero) }

    val imageBitmap by produceState<ImageBitmap?>(null, nativePtr, intSize, frameCount, frameRate) {
        if (intSize.width == 0 || intSize.height == 0 || frameCount <= 0) {
            value = null
            return@produceState
        }

        val renderWidth: Int
        val renderHeight: Int

        if (intSize.width > MAX_RENDER_SIZE_PX || intSize.height > MAX_RENDER_SIZE_PX) {
            val scale = minOf(
                MAX_RENDER_SIZE_PX / intSize.width.toFloat(),
                MAX_RENDER_SIZE_PX / intSize.height.toFloat()
            )
            renderWidth = (intSize.width * scale).roundToInt()
            renderHeight = (intSize.height * scale).roundToInt()
        } else {
            renderWidth = intSize.width
            renderHeight = intSize.height
        }

        val bitmaps = arrayOf(
            createBitmap(renderWidth, renderHeight, Bitmap.Config.ARGB_8888),
            createBitmap(renderWidth, renderHeight, Bitmap.Config.ARGB_8888)
        )
        var currentBufferIndex = 0

        val targetFrameRate = if (frameRate > 0.0) frameRate else 60.0
        val frameTime = (1000 / targetFrameRate).toLong().coerceAtLeast(16)
        var frame = 0

        withContext(Dispatchers.IO) {
            while (isActive && isRenderingActive.get()) {
                val startTime = System.currentTimeMillis()

                val renderBitmap = bitmaps[currentBufferIndex]

                nativeLock.lock()
                try {
                    if (isRenderingActive.get()) {
                        RLottiePlayer.nativeRenderFrame(nativePtr, frame, renderBitmap, renderWidth, renderHeight)
                        val newFrame = renderBitmap.asImageBitmap()
                        newFrame.prepareToDraw()
                        value = newFrame
                    }
                } finally {
                    nativeLock.unlock()
                }

                currentBufferIndex = (currentBufferIndex + 1) % 2

                val renderTime = System.currentTimeMillis() - startTime
                val delayTime = frameTime - renderTime
                if (delayTime > 0) {
                    delay(delayTime)
                }

                frame = (frame + 1) % frameCount
            }
        }
    }

    if (imageBitmap != null) {
        Image(
            bitmap = imageBitmap!!,
            contentDescription = "Lottie Sticker",
            modifier = modifier
                .size(size)
                .onSizeChanged { intSize = it },
            contentScale = ContentScale.Fit
        )
    } else {
        Box(modifier = modifier
            .size(size)
            .onSizeChanged { intSize = it }
        )
    }
}

@Composable
fun WebPImage(
    path: String,
    size: Dp,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = File(path),
        contentDescription = "WebP Sticker",
        contentScale = ContentScale.Fit,
        modifier = modifier.size(size)
    )
}

@Composable
fun StickerWEBMPlayer(
    videoPath: String,
    modifier: Modifier = Modifier
) {
    key(videoPath) {
        AndroidView(
            factory = { context ->
                TextureView(context).apply {
                    isOpaque = false

                    surfaceTextureListener = object : TextureView.SurfaceTextureListener {
                        var player: VPPlayer? = null
                        var surface: Surface? = null

                        override fun onSurfaceTextureAvailable(
                            surfaceTexture: SurfaceTexture,
                            width: Int,
                            height: Int
                        ) {
                            surface = Surface(surfaceTexture)
                            try {
                                player = VPPlayer(videoPath, surface!!)
                                player?.start()
                            } catch (e: IllegalStateException) {
                                e.printStackTrace()
                            }
                        }

                        override fun onSurfaceTextureSizeChanged(
                            surfaceTexture: SurfaceTexture,
                            width: Int,
                            height: Int
                        ) {

                        }

                        override fun onSurfaceTextureDestroyed(surfaceTexture: SurfaceTexture): Boolean {
                            player?.stop()
                            player?.destroy()
                            player = null
                            surface?.release()
                            surface = null
                            return true
                        }

                        override fun onSurfaceTextureUpdated(surfaceTexture: SurfaceTexture) {

                        }
                    }
                }
            },
            modifier = modifier.graphicsLayer(
                compositingStrategy = CompositingStrategy.Offscreen
            )
        )
    }
}

