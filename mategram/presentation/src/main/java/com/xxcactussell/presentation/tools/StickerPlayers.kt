package com.xxcactussell.presentation.tools

import android.util.Log // Import Log
import android.util.LruCache
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import com.xxcactussell.jni.StickerController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.withContext
import java.io.File
import java.util.zip.GZIPInputStream
import kotlin.math.roundToInt

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

object StickerCache {
    private const val CACHE_SIZE = 20 * 1024 * 1024 // 20 MB
    private val memoryCache = object : LruCache<String, Any>(CACHE_SIZE) {
        override fun sizeOf(key: String, value: Any): Int {
            return when (value) {
                is ByteArray -> value.size
                is String -> value.length * 2
                else -> 1
            }
        }
    }

    fun get(key: String): Any? = memoryCache.get(key)
    fun put(key: String, data: Any) { memoryCache.put(key, data) }
}

private fun calculateStickerDisplaySize(stickerWidth: Int, stickerHeight: Int, targetSize: Dp): Pair<Dp, Dp> {
    val aspectRatio = if (stickerHeight > 0) stickerWidth.toFloat() / stickerHeight.toFloat() else 1f

    return if (aspectRatio > 1f) { // Wider than tall
        targetSize to (targetSize / aspectRatio)
    } else { // Taller than wide or square
        (targetSize * aspectRatio) to targetSize
    }
}

@Composable
fun Sticker(
    path: String,
    size: Dp,
    modifier: Modifier = Modifier
) {
    var stickerData by remember(path) { mutableStateOf<Any?>(null) }

    LaunchedEffect(path) {
        stickerData = withContext(Dispatchers.IO) {
            StickerCache.get(path) ?: run {
                try {
                    val file = File(path)
                    if (!file.exists()) {
                        Log.e("Sticker", "Sticker file not found: $path") // Added logging
                        return@withContext null
                    }
                    val bytes = file.readBytes()
                    val data: Any = when {
                        path.endsWith(".tgs", ignoreCase = true) -> decompressGzipIfNeeded(bytes).toString(Charsets.UTF_8)
                        path.endsWith(".webp", ignoreCase = true) -> bytes
                        path.endsWith(".webm", ignoreCase = true) -> bytes
                        else -> {
                            Log.w("Sticker", "Unsupported sticker format: $path") // Added logging
                            return@withContext null
                        }
                    }
                    StickerCache.put(path, data)
                    data
                } catch (e: Exception) {
                    Log.e("Sticker", "Error loading sticker from path: $path", e) // Added logging
                    null
                }
            }
        }
    }

    val targetSizePx = with(LocalDensity.current) { size.toPx().roundToInt() }

    val controller = remember(stickerData, targetSizePx) {
        val data = stickerData ?: return@remember null
        val type = when {
            data is String -> StickerController.StickerType.LOTTIE
            path.endsWith(".webp", ignoreCase = true) -> StickerController.StickerType.WEBP
            path.endsWith(".webm", ignoreCase = true) -> StickerController.StickerType.VP9
            else -> return@remember null
        }
        try {
            StickerController(type, data, targetSizePx, targetSizePx)
        } catch (e: Exception) {
            Log.e("Sticker", "Failed to create sticker controller for $path", e)
            null
        }
    }

    DisposableEffect(controller) {
        onDispose { controller?.close() }
    }

    if (controller != null && controller.stickerWidth > 0 && controller.stickerHeight > 0) {
        val (width, height) = remember(controller.stickerWidth, controller.stickerHeight, size) {
            calculateStickerDisplaySize(
                stickerWidth = controller.stickerWidth,
                stickerHeight = controller.stickerHeight,
                targetSize = size
            )
        }
        StickerPlayer(controller = controller, modifier = modifier.size(width = width, height = height))
    } else if (stickerData != null) {
        // Placeholder for loading or failed stickers
        Spacer(modifier = modifier.size(size))
    }
}

@Composable
fun StickerPlayer(
    controller: StickerController,
    modifier: Modifier = Modifier
) {
    var frameTick by remember { mutableLongStateOf(0L) }

    LaunchedEffect(controller) {
        withContext(Dispatchers.Default) {
            while (isActive) {
                val start = System.currentTimeMillis()
                controller.updateFrame()
                frameTick++

                val workTime = System.currentTimeMillis() - start
                val waitTime = (controller.getNextFrameDelay() - workTime).coerceAtLeast(0)
                delay(waitTime)
            }
        }
    }

    val imageBitmap = remember(controller) { controller.bitmap.asImageBitmap() }

    Spacer(
        modifier = modifier.drawBehind {
            @Suppress("UNUSED_VARIABLE")
            val tick = frameTick

            val x = (size.width - imageBitmap.width) / 2f
            val y = (size.height - imageBitmap.height) / 2f

            drawImage(
                image = imageBitmap,
                topLeft = Offset(x, y)
            )
        }
    )
}