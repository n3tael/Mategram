package com.xxcactussell.presentation.tools

import android.util.Log
import android.util.LruCache
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.xxcactussell.jni.StickerAnimScheduler
import com.xxcactussell.jni.StickerController
import com.xxcactussell.jni.VisibilityState
import com.xxcactussell.utils.PerformanceProfile
import kotlinx.coroutines.Dispatchers
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
        } catch (e: Exception) { data }
    } else data
}

object StickerCache {
    private const val CACHE_SIZE = 20 * 1024 * 1024
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
    if (stickerWidth <= 0 || stickerHeight <= 0) return targetSize to targetSize
    val aspectRatio = stickerWidth.toFloat() / stickerHeight.toFloat()
    return if (aspectRatio > 1f) {
        targetSize to (targetSize / aspectRatio)
    } else {
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
                    if (!file.exists()) return@withContext null

                    val bytes = file.readBytes()
                    val data: Any = when {
                        path.endsWith(".tgs", ignoreCase = true) ->
                            decompressGzipIfNeeded(bytes).toString(Charsets.UTF_8)
                        path.endsWith(".webp", ignoreCase = true) ||
                                path.endsWith(".webm", ignoreCase = true) -> bytes
                        else -> return@withContext null
                    }
                    StickerCache.put(path, data)
                    data
                } catch (e: Exception) {
                    Log.e("Sticker", "Error loading sticker: $path", e)
                    null
                }
            }
        }
    }

    val targetSizePx = with(LocalDensity.current) { size.toPx().roundToInt() }
    val density = LocalDensity.current

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
            Log.e("Sticker", "Failed to create controller: $path", e)
            null
        }
    }

    DisposableEffect(controller) {
        onDispose { controller?.close() }
    }

    if (controller != null) {
        val (width, height) = remember(controller.stickerWidth, controller.stickerHeight, size) {
            calculateStickerDisplaySize(controller.stickerWidth, controller.stickerHeight, size)
        }

        StickerPlayer(
            controller = controller,
            modifier = modifier
                .size(width, height)
                .onGloballyPositioned { coordinates ->
                    val yInWindow = coordinates.positionInWindow().y
                    val screenHeight = with(density) { 2000.dp.toPx() }

                    val margin = 500f

                    val state = when {
                        yInWindow < -margin || yInWindow > screenHeight + margin ->
                            VisibilityState.HIDDEN
                        yInWindow !in 0.0..screenHeight.toDouble() ->
                            VisibilityState.PREPARING
                        else -> VisibilityState.VISIBLE
                    }

                    StickerAnimScheduler.updateState(controller, state)
                }
        )
    } else {
        Spacer(modifier = modifier.size(size))
    }
}

@Composable
fun StickerPlayer(
    controller: StickerController,
    modifier: Modifier = Modifier
) {
    val frameToggle = remember { mutableStateOf(false) }
    val imageBitmap = remember(controller) { controller.bitmap.asImageBitmap() }

    DisposableEffect(controller) {
        controller.onFrameReady = { frameToggle.value = !frameToggle.value }
        onDispose { }
    }

    Canvas(modifier = modifier) {
        frameToggle.value

        drawImage(
            image = imageBitmap,
            dstSize = IntSize(size.width.roundToInt(), size.height.roundToInt()),
            filterQuality = if (PerformanceProfile.isLowEnd) FilterQuality.None else FilterQuality.Low
        )
    }
}