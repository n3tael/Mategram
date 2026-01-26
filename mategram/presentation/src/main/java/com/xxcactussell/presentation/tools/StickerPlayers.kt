package com.xxcactussell.presentation.tools

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import com.xxcactussell.jni.StickerAnimScheduler
import com.xxcactussell.jni.StickerController
import com.xxcactussell.jni.StickerRegistry
import com.xxcactussell.jni.VisibilityState
import com.xxcactussell.utils.PerformanceProfile
import kotlin.math.roundToInt

@Composable
fun Sticker(
    path: String,
    size: Dp,
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current
    val targetSizePx = with(density) { size.toPx().roundToInt() }
    val sourceHash = remember(path) { path.hashCode().toString() }

    val controller = remember(sourceHash, targetSizePx) {
        StickerRegistry.acquire(path, sourceHash, targetSizePx, targetSizePx)
    }

    DisposableEffect(sourceHash) {
        onDispose { StickerRegistry.release(sourceHash) }
    }

    if (controller != null) {
        val displaySize = remember(controller.stickerWidth, controller.stickerHeight, size) {
            calculateStickerDisplaySize(controller.stickerWidth, controller.stickerHeight, size)
        }

        StickerPlayer(
            controller = controller,
            modifier = modifier
                .size(displaySize.first, displaySize.second)
                .onGloballyPositioned { coords ->
                    val y = coords.positionInWindow().y
                    val screenHeight = 2500f
                    val state = when {
                        y < -500f || y > screenHeight + 500f -> VisibilityState.HIDDEN
                        y !in 0.0..screenHeight.toDouble() -> VisibilityState.PREPARING
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
    var frameTick by remember { mutableStateOf(0L) }
    val imageBitmap = remember(controller) { controller.bitmap.asImageBitmap() }

    DisposableEffect(controller) {
        controller.onFrameReady = {
            frameTick++
        }
        onDispose { controller.onFrameReady = null }
    }

    Canvas(modifier = modifier) {
        @Suppress("unused", "UnusedVariable") val tick = frameTick

        drawImage(
            image = imageBitmap,
            dstSize = IntSize(size.width.roundToInt(), size.height.roundToInt()),
            filterQuality = if (PerformanceProfile.isLowEnd) FilterQuality.None else FilterQuality.Low
        )
    }
}

private fun calculateStickerDisplaySize(w: Int, h: Int, target: Dp): Pair<Dp, Dp> {
    if (w <= 0 || h <= 0) return target to target
    val aspect = w.toFloat() / h
    return if (aspect > 1f) target to (target / aspect) else (target * aspect) to target
}