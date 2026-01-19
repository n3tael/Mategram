package com.xxcactussell.presentation.tools

import android.os.Build
import android.view.RoundedCorner
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Modifier.screenStyle(
    elevation: Dp = 8.dp,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceContainerHighest
): Modifier {
    val cornerRadius = rememberDeviceCornerRadius()
    val shape = RoundedCornerShape(cornerRadius)
    return this
        .shadow(
            elevation = elevation,
            shape = shape,
            clip = false
        )
        .clip(shape)
        .background(backgroundColor)
}

@Composable
fun rememberDeviceCornerRadius(defaultRadius: Dp = 16.dp): Dp {
    val density = LocalDensity.current
    val view = LocalView.current

    return remember(view, density) {
        val insets = view.rootWindowInsets
        val corner = insets?.getRoundedCorner(RoundedCorner.POSITION_TOP_LEFT)
        val radiusPx = corner?.radius ?: return@remember defaultRadius
        with(density) { radiusPx.toDp() }
    }
}