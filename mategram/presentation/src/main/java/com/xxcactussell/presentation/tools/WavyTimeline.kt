package com.xxcactussell.presentation.tools

import androidx.annotation.OptIn
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.compose.state.ProgressStateWithTickInterval
import kotlin.math.sin

@OptIn(UnstableApi::class)
@Composable
fun WavyTimeline(
    modifier: Modifier = Modifier,
    state: ProgressStateWithTickInterval,
    seekTo: (Long) -> Unit,
    pause: () -> Unit = {},
    play: () -> Unit = {},
    waveAmplitude: Dp = 3.dp,
    waveFrequency: Float = 0.07f,
    strokeWidth: Dp = 4.dp,
    cursorHeight: Dp = 24.dp,
    cursorWidth: Dp = 8.dp,
    pressedCursorWidth: Dp = 12.dp,
    trackColor: Color = Color.White.copy(alpha = 0.2f),
    bufferedColor: Color = Color.White,
    playedColor: Color = Color.White
) {
    var isSeeking by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .height(cursorHeight * 2)
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures { offset ->
                    val ratio = offset.x / size.width
                    seekTo((state.durationMs * ratio).toLong())
                }
            }
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onDragStart = {
                        isSeeking = true
                        pause()
                    },
                    onDragEnd = {
                        isSeeking = false
                        play()
                    },
                    onHorizontalDrag = { change, _ ->
                        val ratio = change.position.x / size.width
                        seekTo((state.durationMs * ratio.coerceIn(0f, 1f)).toLong())
                    }
                )
            }
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val width = size.width
            val centerY = size.height / 2

            val playedRatio = (state.currentPositionMs.toFloat() / state.durationMs.toFloat()).coerceIn(0f, 1f)
            val bufferedRatio = (state.bufferedPositionMs.toFloat() / state.durationMs.toFloat()).coerceIn(0f, 1f)

            val playedX = width * playedRatio
            val bufferedX = width * bufferedRatio

            val strokePx = strokeWidth.toPx()
            val amplitudePx = waveAmplitude.toPx()
            val cursorWidthPx = if (isSeeking) pressedCursorWidth.toPx() else cursorWidth.toPx()
            val cursorHeightPx = cursorHeight.toPx()

            drawLine(
                color = trackColor,
                start = Offset(bufferedX, centerY),
                end = Offset(width, centerY),
                strokeWidth = strokePx,
                cap = StrokeCap.Round
            )

            if (bufferedX > playedX) {
                drawLine(
                    color = bufferedColor,
                    start = Offset(playedX, centerY),
                    end = Offset(bufferedX, centerY),
                    strokeWidth = strokePx,
                    cap = StrokeCap.Butt
                )
            }

            val wavePath = Path()
            wavePath.moveTo(0f, centerY)

            val stepX = 2f
            var x = 0f
            while (x < playedX) {
                val y = centerY + amplitudePx * sin(x * waveFrequency)
                wavePath.lineTo(x, y)
                x += stepX
            }
            wavePath.lineTo(playedX, centerY)

            drawPath(
                path = wavePath,
                color = playedColor,
                style = Stroke(width = strokePx, cap = StrokeCap.Round)
            )

            drawRoundRect(
                color = playedColor,
                topLeft = Offset(playedX - cursorWidthPx / 2, centerY - cursorHeightPx / 2),
                size = Size(cursorWidthPx, cursorHeightPx),
                cornerRadius = CornerRadius(cursorWidthPx / 2, cursorWidthPx / 2)
            )
        }
    }
}