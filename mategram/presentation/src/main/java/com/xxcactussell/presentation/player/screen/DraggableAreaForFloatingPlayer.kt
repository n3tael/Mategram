package com.xxcactussell.presentation.player.screen

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.xxcactussell.mategram.presentation.R
import com.xxcactussell.presentation.player.viewmodel.GlobalPlayerViewModel
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun DraggableAreaForFloatingPlayer(
    modifier: Modifier,
    viewModel: GlobalPlayerViewModel,
    onWidgetClick: (Long, Long?) -> Unit
) {
    val scope = rememberCoroutineScope()
    val density = LocalDensity.current
    val configuration = LocalConfiguration.current

    var childSize by remember { mutableStateOf(54.dp) }
    val childSizePx by remember { derivedStateOf { with(density) { childSize.toPx() } } }

    val screenWidth = with(density) { configuration.screenWidthDp.dp.toPx() }
    val screenHeight = with(density) { configuration.screenHeightDp.dp.toPx() }
    val stickyOffsetPx = with(density) { 2.dp.toPx() }

    val closeSize = 80.dp
    val closeSizePx = with(density) { closeSize.toPx() }
    val closeTargetY = -with(density) { 120.dp.toPx() }
    val closeHiddenY = 250f

    val offset = remember { Animatable(Offset(-stickyOffsetPx, screenHeight / 4), Offset.VectorConverter) }
    val closeButtonOffset = remember { Animatable(Offset(0f, closeHiddenY), Offset.VectorConverter) }

    var isOnLeftSide by remember { mutableStateOf(offset.value.x < screenWidth / 2) }
    var dragPosition by remember { mutableStateOf(Offset.Zero) }
    var isDragging by remember { mutableStateOf(false) }
    var isInsideCloseZone by remember { mutableStateOf(false) }

    val jellySpring = spring<Offset>(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow)

    LaunchedEffect(isDragging) {
        if (isDragging) {
            closeButtonOffset.animateTo(Offset(0f, closeTargetY), spring(stiffness = Spring.StiffnessLow))
        } else {
            closeButtonOffset.animateTo(Offset(0f, closeHiddenY))
            isInsideCloseZone = false
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset {
                    IntOffset(
                        closeButtonOffset.value.x.roundToInt(),
                        closeButtonOffset.value.y.roundToInt()
                    )
                }
                .size(closeSize)
                .clip(CircleShape)
                .background(if (isInsideCloseZone) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painterResource(R.drawable.close_24px),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }

        FloatingPlayerWidget(
            viewModel = viewModel,
            onWidgetClick = onWidgetClick,
            onSizeChanged = { size -> childSize = size },
            isOnLeftSide = isOnLeftSide,
            modifier = Modifier
                .offset {
                    IntOffset(
                        offset.value.x.roundToInt(),
                        offset.value.y.roundToInt()
                    )
                }
                .pointerInput(childSizePx) {
                    detectDragGestures(
                        onDragStart = {
                            isDragging = true
                            dragPosition = offset.value
                        },
                        onDragEnd = {
                            isDragging = false
                            scope.launch {
                                if (isInsideCloseZone) {
                                    launch { closeButtonOffset.animateTo(Offset(0f, screenHeight)) }
                                    offset.animateTo(Offset(offset.value.x, screenHeight + 200f))
                                    viewModel.closePlayer()
                                } else {
                                    val centerX = offset.value.x + childSizePx / 2
                                    val targetX = if (centerX < screenWidth / 2) -stickyOffsetPx else screenWidth - childSizePx + stickyOffsetPx
                                    offset.animateTo(Offset(targetX, offset.value.y), jellySpring)
                                }
                            }
                        },
                        onDrag = { change, dragAmount ->
                            change.consume()

                            dragPosition += dragAmount

                            val fingerCenterX = dragPosition.x + childSizePx / 2
                            val fingerCenterY = dragPosition.y + childSizePx / 2

                            val inHorizontalZone = fingerCenterX > screenWidth * 0.2f && fingerCenterX < screenWidth * 0.8f
                            val inVerticalZone = fingerCenterY > screenHeight * 0.7f

                            if (inHorizontalZone && inVerticalZone) {
                                isInsideCloseZone = true

                                val btnCenterX = screenWidth / 2
                                val btnCenterY = screenHeight + closeTargetY + (closeSizePx / 2)

                                val targetWidgetPos = Offset(
                                    btnCenterX - (childSizePx / 2),
                                    btnCenterY - (childSizePx / 2)
                                )

                                val magnetPos = (dragPosition + targetWidgetPos) / 2f

                                scope.launch {
                                    offset.animateTo(magnetPos)
                                    val btnPullX = (fingerCenterX - screenWidth / 2) * 0.15f
                                    closeButtonOffset.animateTo(Offset(btnPullX, closeTargetY))
                                }
                            } else {
                                isInsideCloseZone = false
                                scope.launch {
                                    offset.animateTo(dragPosition)
                                    closeButtonOffset.animateTo(Offset(0f, closeTargetY))
                                }
                            }
                            isOnLeftSide = offset.value.x < screenWidth / 2
                        }
                    )
                }
        )
    }
}