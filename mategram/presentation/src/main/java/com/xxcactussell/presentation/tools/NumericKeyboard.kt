package com.xxcactussell.presentation.tools

import android.app.Activity
import androidx.activity.compose.LocalActivity
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xxcactussell.mategram.presentation.R
import com.xxcactussell.presentation.localization.localizedString

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun NumericKeyboard(
    onNumberClick: (digit: Int) -> Unit,
    onBackspaceClick: () -> Unit,
    onSendClick: () -> Unit
) {
    val haptic = LocalHapticFeedback.current

    val activity: Activity? = LocalActivity.current
    val isLandscape = if (activity != null) {
        val windowSizeClass = calculateWindowSizeClass(activity)
        windowSizeClass.heightSizeClass == WindowHeightSizeClass.Compact
    } else {
        false
    }

    val keyboardRows = listOf(
        listOf(1, 2, 3),
        listOf(4, 5, 6),
        listOf(7, 8, 9)
    )

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Surface(
            modifier = if (isLandscape) Modifier.align(Alignment.BottomCenter).fillMaxWidth() else Modifier.widthIn(max = 600.dp).align(Alignment.BottomCenter),
            shape = RoundedCornerShape(topStart = 64.dp, topEnd = 64.dp),
            color = MaterialTheme.colorScheme.surfaceContainerHighest
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = if (isLandscape) 16.dp else 62.dp, horizontal = 16.dp)
                    .windowInsetsPadding(WindowInsets.navigationBars),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(if (isLandscape) 2.dp else 16.dp)
            ) {
                val rowModifier = if (isLandscape) {
                    Modifier.widthIn(max = 278.dp).height(24.dp)
                } else {
                    Modifier.width(278.dp).height(82.dp)
                }
                val horizontalArrangement = Arrangement.spacedBy(
                    if (isLandscape) 2.dp else 16.dp,
                    Alignment.CenterHorizontally
                )

                keyboardRows.forEach { rowNumbers ->
                    val interactionSources = remember { rowNumbers.map { MutableInteractionSource() } }
                    val pressedStates = interactionSources.map { it.collectIsPressedAsState().value }
                    val pressedIndex = pressedStates.indexOf(true).takeIf { it != -1 }

                    Row(
                        modifier = rowModifier,
                        horizontalArrangement = horizontalArrangement
                    ) {
                        rowNumbers.forEachIndexed { index, number ->
                            NumberButton(
                                modifier = Modifier,
                                number = number,
                                onClick = {
                                    haptic.performHapticFeedback(HapticFeedbackType.KeyboardTap)
                                    onNumberClick(number)
                                },
                                isLandscape = isLandscape,
                                interactionSource = interactionSources[index],
                                isPressed = pressedIndex == index,
                                isOtherPressed = pressedIndex != null && pressedIndex != index
                            )
                        }
                    }
                }

                val actionInteractionSources = remember { List(3) { MutableInteractionSource() } }
                val actionPressedStates = actionInteractionSources.map { it.collectIsPressedAsState().value }
                val actionPressedIndex = actionPressedStates.indexOf(true).takeIf { it != -1 }

                Row(
                    modifier = rowModifier,
                    horizontalArrangement = Arrangement.spacedBy(if (isLandscape) 2.dp else 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ActionButton(
                        onClick = {
                            haptic.performHapticFeedback(HapticFeedbackType.Reject)
                            onBackspaceClick()
                        },
                        modifier = Modifier,
                        isLandscape = isLandscape,
                        interactionSource = actionInteractionSources[0],
                        isPressed = actionPressedIndex == 0,
                        isOtherPressed = actionPressedIndex != null && actionPressedIndex != 0,
                        content = {
                            Icon(
                                painterResource(R.drawable.backspace_24px),
                                modifier = Modifier.size(28.dp),
                                contentDescription = "Backspace"
                            )
                        }
                    )
                    NumberButton(
                        number = 0,
                        onClick = {
                            haptic.performHapticFeedback(HapticFeedbackType.KeyboardTap)
                            onNumberClick(0)
                        },
                        modifier = Modifier,
                        isLandscape = isLandscape,
                        interactionSource = actionInteractionSources[1],
                        isPressed = actionPressedIndex == 1,
                        isOtherPressed = actionPressedIndex != null && actionPressedIndex != 1
                    )
                    SendButton(
                        onClick = {
                            haptic.performHapticFeedback(HapticFeedbackType.Confirm)
                            onSendClick
                        },
                        content = {
                            Icon(
                                painterResource(R.drawable.start_24px),
                                modifier = Modifier.size(28.dp),
                                contentDescription = localizedString("Send")
                            )
                        },
                        isLandscape = isLandscape,
                        interactionSource = actionInteractionSources[2],
                        isPressed = actionPressedIndex == 2,
                        isOtherPressed = actionPressedIndex != null && actionPressedIndex != 2
                    )
                }
            }
        }
    }
}

enum class ButtonState { Pressed, Released, Shrunk }
@Composable
fun RowScope.AnimatedPressButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource?,
    isPressed: Boolean,
    isOtherPressed: Boolean,
    isLandscape: Boolean,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    shape: Shape = RoundedCornerShape(26.dp),
    content: @Composable RowScope.() -> Unit
) {
    val buttonState = when {
        isPressed -> ButtonState.Pressed
        isOtherPressed -> ButtonState.Shrunk
        else -> ButtonState.Released
    }

    val transition = updateTransition(
        targetState = buttonState,
        label = "buttonTransition"
    )

    val buttonModifier = if (isLandscape) {
        val animatedWeight by transition.animateFloat(
            label = "buttonWeightAnimation",
            transitionSpec = {
                spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            }
        ) { state ->
            when (state) {
                ButtonState.Pressed -> 2.0f
                ButtonState.Released -> 1.0f
                ButtonState.Shrunk -> 0.5f
            }
        }
        modifier
            .fillMaxHeight()
            .weight(animatedWeight)
    } else {
        val animatedWidth by transition.animateDp(
            label = "buttonWidthAnimation",
            transitionSpec = {
                spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            }
        ) { state ->
            when (state) {
                ButtonState.Pressed -> 122.dp
                ButtonState.Released -> 82.dp
                ButtonState.Shrunk -> 62.dp
            }
        }
        modifier
            .fillMaxHeight()
            .width(animatedWidth)
    }

    Button(
        onClick = onClick,
        modifier = buttonModifier,
        interactionSource = interactionSource,
        colors = colors,
        shape = shape,
        contentPadding = PaddingValues(12.dp, 2.dp),
        content = content
    )
}

@Composable
private fun RowScope.NumberButton(
    number: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isLandscape: Boolean,
    interactionSource: MutableInteractionSource,
    isPressed: Boolean,
    isOtherPressed: Boolean
) {
    AnimatedPressButton(
        onClick = onClick,
        modifier = modifier,
        interactionSource = interactionSource,
        isPressed = isPressed,
        isOtherPressed = isOtherPressed,
        isLandscape = isLandscape,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.inversePrimary,
            contentColor = MaterialTheme.colorScheme.primary
        ),
        content = {
            Text(
                text = number.toString(),
                style = if (isLandscape) MaterialTheme.typography.titleSmall else MaterialTheme.typography.displaySmall
            )
        }
    )
}

@Composable
private fun RowScope.ActionButton(
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit,
    modifier: Modifier = Modifier,
    isLandscape: Boolean,
    interactionSource: MutableInteractionSource,
    isPressed: Boolean,
    isOtherPressed: Boolean
) {
    AnimatedPressButton(
        onClick = onClick,
        modifier = modifier,
        interactionSource = interactionSource,
        isPressed = isPressed,
        isOtherPressed = isOtherPressed,
        isLandscape = isLandscape,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer
        ),
        content = content
    )
}

@Composable
fun RowScope.SendButton(
    onClick: () -> () -> Unit,
    content: @Composable RowScope.() -> Unit,
    modifier: Modifier = Modifier,
    isLandscape: Boolean,
    interactionSource: MutableInteractionSource?,
    isPressed: Boolean,
    isOtherPressed: Boolean
) {
    AnimatedPressButton(
        onClick = onClick(),
        modifier = modifier,
        interactionSource = interactionSource,
        isPressed = isPressed,
        isOtherPressed = isOtherPressed,
        isLandscape = isLandscape,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        content = content
    )
}


@Preview(showBackground = true)
@Composable
fun NumericKeyboardPreview() {
    MaterialTheme {
        NumericKeyboard(onNumberClick = {}, onBackspaceClick = {}, onSendClick = {})
    }
}