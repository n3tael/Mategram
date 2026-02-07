package com.xxcactussell.presentation.tools

import androidx.compose.foundation.Canvas
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AudioWaveform(
    waveform: ByteArray,
    progress: Float, // 0f..1f
    modifier: Modifier = Modifier,
    activeColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    inactiveColor: Color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.4f)
) {
    Canvas(modifier = modifier) {
        val barWidth = 2.dp.toPx()
        val gap = 2.dp.toPx()
        val count = (size.width / (barWidth + gap)).toInt()

        val step = if (waveform.isNotEmpty()) waveform.size.toFloat() / count else 1f

        for (i in 0 until count) {
            val index = (i * step).toInt().coerceIn(waveform.indices)
            val rawHeight = if (waveform.isNotEmpty()) (waveform[index].toInt() and 0x1F).toFloat() / 31f else 0.2f
            val barHeight = (rawHeight * size.height).coerceAtLeast(size.height * 0.1f)

            val x = i * (barWidth + gap)
            val y = (size.height - barHeight) / 2

            val isPlayed = (x / size.width) < progress

            drawRoundRect(
                color = if (isPlayed) activeColor else inactiveColor,
                topLeft = Offset(x, y),
                size = Size(barWidth, barHeight),
                cornerRadius = CornerRadius(2.dp.toPx(), 2.dp.toPx())
            )
        }
    }
}