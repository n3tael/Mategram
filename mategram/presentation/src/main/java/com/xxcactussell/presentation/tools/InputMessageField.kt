package com.xxcactussell.presentation.tools

import android.content.ClipDescription
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material.icons.outlined.EmojiEmotions
import androidx.compose.material.icons.outlined.Videocam
import androidx.compose.material.icons.rounded.AttachFile
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.GraphicEq
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipAnchorPosition
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropEvent
import androidx.compose.ui.draganddrop.DragAndDropTarget
import androidx.compose.ui.draganddrop.toAndroidDragEvent
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.xxcactussell.presentation.localization.localizedString
import com.xxcactussell.presentation.messages.model.InputEvent
import com.xxcactussell.presentation.messages.model.MessagesUiState
import com.xxcactussell.presentation.messages.model.RecordingMode
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun InputMessageField(
    state: MessagesUiState,
    onEvent: (Any) -> Unit
) {
    val textFieldScrollState = rememberScrollState()
    val isOverflowed = rememberSaveable { mutableStateOf(false) }
    val tooltipState = rememberTooltipState()
    val coroutineScope = rememberCoroutineScope()

    val dropTarget = remember(onEvent) {
        object : DragAndDropTarget {
            override fun onDrop(event: DragAndDropEvent): Boolean {
                val dragEvent = event.toAndroidDragEvent()
                val clipData = dragEvent.clipData

                if (clipData != null && clipData.itemCount > 0) {
                    val item = clipData.getItemAt(0)
                    val textToDrop = item.text

                    if (textToDrop != null) {
                        onEvent(InputEvent.TextDropped(textToDrop.toString()))
                        return true
                    }
                }
                return false
            }
        }
    }

    Row(
        Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .imePadding()
            .padding(
                start = WindowInsets.displayCutout.asPaddingValues().calculateStartPadding(LocalLayoutDirection.current),
                end = WindowInsets.displayCutout.asPaddingValues().calculateEndPadding(LocalLayoutDirection.current),
                bottom = WindowInsets.displayCutout.asPaddingValues().calculateBottomPadding(),
            )
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        Row(
            Modifier
                .weight(1f)
                .heightIn(min = 64.dp)
                .shadow(elevation = 1.dp, shape = RoundedCornerShape(32.dp))
                .clip(RoundedCornerShape(32.dp))
                .background(MaterialTheme.colorScheme.surfaceContainerLowest)
                .dragAndDropTarget(
                    shouldStartDragAndDrop = { event ->
                        event
                            .toAndroidDragEvent()
                            .clipDescription
                            ?.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN) == true
                    },
                    target = dropTarget
                ),
            verticalAlignment = Alignment.Bottom
        ) {
            Spacer(Modifier.width(8.dp))
            if (state.attachmentEntries.isNotEmpty()) {
                IconButton(
                    modifier = Modifier.padding(vertical = 8.dp),
                    onClick = {
                        if (state.selectedMediaUris.isNotEmpty()) {
                            onEvent(InputEvent.ClearAttachments())
                        } else {
                            onEvent(InputEvent.OpenAttachmentsMenu)
                        }
                    },
                ) {
                    AnimatedContent(
                        targetState = state.selectedMediaUris.isNotEmpty(),
                        label = "AttachmentIcon"
                    ) { hasMedia ->
                        Icon(
                            if (hasMedia) {
                                Icons.Rounded.Close
                            } else {
                                Icons.Rounded.AttachFile
                            },
                            contentDescription = if (hasMedia) {
                                "Cancel attachment"
                            } else {
                                "Attach file"
                            }
                        )
                    }
                }
            }
            Box (
                Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .heightIn(min = 64.dp, max = 128.dp)
                        .verticalScroll(textFieldScrollState, reverseScrolling = true),
                    contentAlignment = Alignment.CenterStart
                ) {
                    BasicTextField(
                        enabled = state.chat?.permissions?.canSendBasicMessages == true,
                        value = state.inputMessage,
                        onValueChange = { onEvent(InputEvent.TextChanged(it)) },
                        cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 20.dp)
                            .wrapContentHeight(
                                align = Alignment.Bottom,
                                unbounded = true
                            )
                            .animateContentSize(
                                animationSpec = spring(
                                    dampingRatio = Spring.DampingRatioMediumBouncy,
                                    stiffness = Spring.StiffnessLow
                                )
                            ),
                        onTextLayout = { textLayoutResult ->
                            isOverflowed.value = textLayoutResult.lineCount > 5
                        },
                        textStyle = TextStyle(
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize
                        ),
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Sentences,
                            autoCorrectEnabled = true,
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Default
                        ),
                        decorationBox = { innerTextField ->
                            Box(
                                contentAlignment = Alignment.CenterStart
                            ) {
                                if (state.inputMessage.isEmpty()) {
                                    Text(
                                        text = if(state.chat?.permissions?.canSendBasicMessages == true) localizedString("Message") else localizedString("PlainTextRestrictedHint"),
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                                        overflow = TextOverflow.Ellipsis,
                                        maxLines = 1
                                    )
                                }
                                innerTextField()
                            }
                        }
                    )

                }
                AnimatedContent(
                    isOverflowed.value,
                    transitionSpec = {
                        (fadeIn()) togetherWith (fadeOut())
                    }
                ) { isOverflowed ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.TopCenter)
                            .height(20.dp)
                            .background(
                                brush = Brush.verticalGradient(
                                    colors =
                                        if (isOverflowed) {
                                            listOf(
                                                MaterialTheme.colorScheme.surfaceContainerLowest,
                                                Color.Transparent
                                            )
                                        } else {
                                            listOf(
                                                Color.Transparent,
                                                Color.Transparent
                                            )
                                        }
                                )
                            )
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .height(20.dp)
                        .background(
                            brush = Brush.verticalGradient(
                                colors =
                                    listOf(
                                        Color.Transparent,
                                        MaterialTheme.colorScheme.surfaceContainerLowest
                                    )
                            )
                        )
                )
            }

            IconButton(
                modifier = Modifier.padding(0.dp, 8.dp, 8.dp, 8.dp),
                onClick = { /* TODO: Handle emoji picker */ }
            ) {
                Icon(
                    Icons.Outlined.EmojiEmotions,
                    contentDescription = "Emoji",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        TooltipBox(
            positionProvider = TooltipDefaults.rememberTooltipPositionProvider(
                TooltipAnchorPosition.Above,
                spacingBetweenTooltipAndAnchor = 8.dp
            ),
            tooltip = {
                if (state.recordingMode !is RecordingMode.Text) {
                    PlainTooltip {
                        Text(
                            when (state.recordingMode) {
                                is RecordingMode.Audio -> localizedString("HoldToAudio")
                                is RecordingMode.Video -> localizedString("HoldToVideo")
                                is RecordingMode.Text -> ""
                            }
                        )
                    }
                }
            },
            state = tooltipState
        ) {
            FloatingActionButton(
                modifier = Modifier
                    .padding(bottom = 4.dp),
                onClick = {
                    if (state.inputMessage.isNotBlank() || state.selectedMediaUris.isNotEmpty()) {
                        onEvent(InputEvent.SendClicked)
                    } else if (state.recordingMode != RecordingMode.Text) {
                        onEvent(InputEvent.SwitchRecordingMode)
                        coroutineScope.launch {
                            tooltipState.show()
                        }
                    }
                },
                containerColor = MaterialTheme.colorScheme.tertiaryContainer
            ) {

                val currentIcon: ImageVector = if (state.inputMessage.isNotBlank() || state.selectedMediaUris.isNotEmpty()) {
                    Icons.AutoMirrored.Rounded.Send
                } else {
                    when (state.recordingMode) {
                        is RecordingMode.Audio -> Icons.Rounded.GraphicEq
                        is RecordingMode.Video -> Icons.Outlined.Videocam
                        else -> Icons.AutoMirrored.Rounded.Send
                    }
                }

                AnimatedContent(
                    targetState = currentIcon,
                    label = "SendOrRecordIcon",
                    transitionSpec = {
                        (scaleIn() + fadeIn()) togetherWith (scaleOut() + fadeOut())
                    }
                ) { icon ->
                    Icon(icon, contentDescription = "Send")
                }
            }
        }
    }
}