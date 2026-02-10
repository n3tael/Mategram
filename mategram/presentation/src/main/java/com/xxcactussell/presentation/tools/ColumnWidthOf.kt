package com.xxcactussell.presentation.tools

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.collections.get

@Composable
fun ColumnWidthOf(
    modifier: Modifier = Modifier,
    rulerId: Any,
    horizontalSpacers: Dp = 0.dp,
    content: @Composable () -> Unit
) {
    Layout(content = content, modifier = modifier) { measurables, constraints ->
        val rulerMeasurable = measurables.firstOrNull { it.layoutId == rulerId }
        val otherMeasurables = measurables.filter { it.layoutId != rulerId }

        if (rulerMeasurable == null) {
            val placeables = measurables.map { it.measure(constraints) }
            val width = placeables.maxOfOrNull { it.width } ?: 0
            val height = placeables.sumOf { it.height }
            return@Layout layout(width, height) {
                var y = 0
                placeables.forEach {
                    it.placeRelative(0, y)
                    y += it.height
                }
            }
        }

        val rulerPlaceable = rulerMeasurable.measure(constraints)
        val width = rulerPlaceable.width
        val otherConstraints = constraints.copy(minWidth = width, maxWidth = width)
        val otherPlaceables = otherMeasurables.map { it.measure(otherConstraints) }

        val spacerHeight = horizontalSpacers.roundToPx()

        val totalSpacersHeight = if (measurables.size > 1) {
            (measurables.size - 1) * spacerHeight
        } else {
            0
        }

        val totalHeight = rulerPlaceable.height + otherPlaceables.sumOf { it.height } + totalSpacersHeight

        layout(width, totalHeight) {
            var yPosition = 0

            measurables.forEach { measurable ->
                val placeable = if (measurable.layoutId == rulerId) {
                    rulerPlaceable
                } else {
                    val index = otherMeasurables.indexOf(measurable)
                    otherPlaceables[index]
                }

                placeable.placeRelative(0, yPosition)
                yPosition += placeable.height + (if (measurable != measurables.last()) spacerHeight else 0)
            }
        }
    }
}