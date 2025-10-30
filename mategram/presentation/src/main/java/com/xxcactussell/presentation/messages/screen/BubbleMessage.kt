package com.xxcactussell.presentation.messages.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.xxcactussell.presentation.chats.screen.ChatAvatar
import com.xxcactussell.presentation.messages.model.MessageUiItem
import com.xxcactussell.presentation.messages.model.getAvatar
import kotlin.math.abs

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun BubbleMessage(
    message: MessageUiItem,
    isOutgoing: Boolean,
    isUnread: Boolean = false,
    hasBubble: Boolean,
    topCorner: Dp,
    bottomCorner: Dp,
    needSenderName: Boolean,
    needAvatar: Boolean,
    isGroup: Boolean,
    content: @Composable (() -> Unit)
) {
    val alignment = if (isOutgoing) Alignment.Companion.CenterEnd else Alignment.Companion.CenterStart
    val backgroundColor = if (!hasBubble) {
        Color.Companion.Transparent
    } else {
        if (isOutgoing) {
            MaterialTheme.colorScheme.primaryContainer
        } else {
            MaterialTheme.colorScheme.secondaryContainer
        }
    }

    val shape = if (isOutgoing) {
        RoundedCornerShape(18.dp, topCorner, bottomCorner, 18.dp)
    } else {
        RoundedCornerShape(topCorner, 18.dp, 18.dp, bottomCorner)
    }

    val startPadding = if(isOutgoing) 64.dp
    else {
        if (isGroup) 42.dp
        else 0.dp
    }

    Box(
        modifier = Modifier.Companion
            .fillMaxWidth()
            .padding(
                top = if (topCorner == 18.dp) 8.dp else 0.dp,
                start = startPadding,
                end = if (isOutgoing) 0.dp else 64.dp
            )
    ) {
        Column(
            modifier = Modifier.Companion.align(alignment)
        ) {
            if (needSenderName) {
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = message.getAvatar()?.title ?: "@",
                    style = MaterialTheme.typography.labelLargeEmphasized,
                    color = listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.secondary,
                        MaterialTheme.colorScheme.tertiary
                    )[abs(message.getAvatar()?.chatId?.rem(3) ?: 0).toInt()]
                )
            }
            Surface(
                color = backgroundColor,
                shape = shape
            ) {
                content()
            }

            if (needAvatar) {
                Box(Modifier.height(0.dp)) {
                    ChatAvatar(
                        modifier = Modifier
                            .requiredSize(42.dp)
                            .size(42.dp)
                            .offset(-startPadding - 4.dp, (-21).dp),
                        state = message.getAvatar(),
                        isPinned = false,
                        isOnline = false
                    )
                }
            }
            /* TODO REACTIONS
            Row {

            }
            */
        }
    }
}