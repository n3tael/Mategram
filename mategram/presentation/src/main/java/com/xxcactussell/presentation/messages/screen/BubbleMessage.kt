package com.xxcactussell.presentation.messages.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccessTime
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.ErrorOutline
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.xxcactussell.domain.messages.model.Message
import com.xxcactussell.domain.messages.model.MessageStatus
import com.xxcactussell.presentation.chats.screen.ChatAvatar
import com.xxcactussell.presentation.messages.model.MessageUiItem
import com.xxcactussell.presentation.messages.model.getAvatar
import com.xxcactussell.presentation.messages.model.getReactions
import com.xxcactussell.presentation.messages.model.isOutgoing
import kotlin.concurrent.atomics.AtomicLongArray
import kotlin.math.abs

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun BubbleMessage(
    message: MessageUiItem,
    isUnread: Boolean,
    isOutgoing: Boolean,
    hasBubble: Boolean,
    topCorner: Dp,
    bottomCorner: Dp,
    needSenderName: Boolean,
    needAvatar: Boolean,
    isGroup: Boolean,
    onReplyClicked: (Long) -> Unit,
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

    val sendingState = when (message) {
        is MessageUiItem.MessageItem -> message.message.sendingState
        is MessageUiItem.AlbumItem -> {
            if (message.messages.any { it.message.sendingState is MessageStatus.Failed }) {
                message.messages.firstOrNull { it.message.sendingState is MessageStatus.Failed }?.message?.sendingState
            } else if (message.messages.any { it.message.sendingState is MessageStatus.Pending }) {
                message.messages.firstOrNull { it.message.sendingState is MessageStatus.Pending }?.message?.sendingState
            } else {
                null
            }
        }
        else -> null
    }

    val replyingMessage : Message? = when (message) {
        is MessageUiItem.MessageItem -> if (message.message.replyTo != null) message.message else null
        is MessageUiItem.AlbumItem -> {
            message.messages.firstOrNull { it.message.replyTo != null }?.message
        }
        else -> null
    }

    val statusBackgroundColor = when(sendingState) {
        is MessageStatus.Failed -> {
            MaterialTheme.colorScheme.error
        }
        else -> {
            Color.Transparent
        }
    }

    val statusIcon = when(sendingState) {
        is MessageStatus.Failed -> {
            Icons.Rounded.ErrorOutline
        }
        is MessageStatus.Pending -> {
            Icons.Rounded.AccessTime
        }
        else -> {
            Icons.Rounded.Check
        }
    }

    val statusIconColor = when(sendingState) {
        is MessageStatus.Failed -> MaterialTheme.colorScheme.onError
        else -> MaterialTheme.colorScheme.primary
    }

    val statusIconSize = when(sendingState) {
        is MessageStatus.Failed -> 12.dp
        else -> 16.dp
    }

    Box(
        modifier = Modifier.Companion
            .fillMaxWidth()
            .padding(
                start = startPadding,
                end = if (isOutgoing) 0.dp else 64.dp
            )
    ) {
        Column(
            modifier = Modifier.Companion.align(alignment),
            horizontalAlignment = if (isOutgoing) Alignment.End else Alignment.Start
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

            if (replyingMessage != null) {
                ReplyMarkup(message = replyingMessage, isOutgoing, onReplyClicked)
                Spacer(modifier = Modifier.height(4.dp))
            }

            Box {
                Surface(
                    color = backgroundColor,
                    shape = shape
                ) {
                    content()
                }
                if (isUnread && isOutgoing) {
                    Surface(
                        modifier = Modifier
                            .size(16.dp)
                            .align(Alignment.BottomStart)
                            .offset((-20).dp),
                        color = statusBackgroundColor,
                        shape = CircleShape
                    ) {
                        Icon(
                            modifier = Modifier.requiredSize(statusIconSize),
                            imageVector = statusIcon,
                            tint = statusIconColor,
                            contentDescription = ""
                        )
                    }
                }
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

            val reactions = message.getReactions()
            if (reactions.isNotEmpty()) {
                Spacer(Modifier.height(4.dp))
                FlowRow(
                    maxItemsInEachRow = 4,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    reactions.forEach {
                        ReactionChip(it) { }
                    }
                }
            }
        }
    }
}