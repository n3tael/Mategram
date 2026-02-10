package com.xxcactussell.presentation.tools

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import com.xxcactussell.jni.StickerAnimScheduler
import com.xxcactussell.jni.StickerController
import com.xxcactussell.jni.StickerRegistry
import com.xxcactussell.utils.PerformanceProfile
import kotlin.math.roundToInt

@Composable
fun Sticker(
    modifier: Modifier = Modifier,
    path: String,
    size: Dp,
    color: Color? = Color.Unspecified
) {
    val density = LocalDensity.current
    val targetSizePx = with(density) { size.toPx().roundToInt() }
    val sourceHash = remember(path) { path.hashCode().toString() }

    val tintColorInt = remember(color) {
        if (color == Color.Unspecified) 0
        else color?.toArgb() ?: 0
    }

    val controller = remember(sourceHash, targetSizePx) {
        StickerRegistry.acquire(path, sourceHash, targetSizePx, targetSizePx, tintColorInt)
    }

    DisposableEffect(controller) {
        StickerAnimScheduler.add(controller)
        onDispose {
            StickerRegistry.release(path, targetSizePx, targetSizePx, tintColorInt)
        }
    }

    val displaySize = remember(controller.stickerWidth, controller.stickerHeight, size) {
        calculateStickerDisplaySize(controller.stickerWidth, controller.stickerHeight, size)
    }

    StickerPlayer(
        controller = controller,
        modifier = modifier.size(displaySize.first, displaySize.second)
    )
}

@Composable
fun StickerPlayer(
    controller: StickerController,
    modifier: Modifier = Modifier
) {
    var frameTick by remember { mutableLongStateOf(0L) }
    var isContentReady by remember(controller) { mutableStateOf(controller.hasRenderedFirstFrame) }

    val imageBitmap = remember(controller) { controller.bitmap.asImageBitmap() }

    DisposableEffect(controller) {
        val listener: () -> Unit = {
            frameTick++
            if (!isContentReady) {
                isContentReady = true
            }
        }

        controller.addFrameListener(listener)

        onDispose {
            controller.removeFrameListener(listener)
        }
    }

    Canvas(modifier = modifier) {
        @Suppress("unused", "UnusedVariable") val tick = frameTick
        if (isContentReady) {
            drawImage(
                image = imageBitmap,
                dstSize = IntSize(size.width.roundToInt(), size.height.roundToInt()),
                filterQuality = if (PerformanceProfile.isLowEnd) FilterQuality.None else FilterQuality.Low
            )
        }
    }
}

private fun calculateStickerDisplaySize(w: Int, h: Int, target: Dp): Pair<Dp, Dp> {
    if (w <= 0 || h <= 0) return target to target
    val aspect = w.toFloat() / h
    return if (aspect > 1f) target to (target / aspect) else (target * aspect) to target
}