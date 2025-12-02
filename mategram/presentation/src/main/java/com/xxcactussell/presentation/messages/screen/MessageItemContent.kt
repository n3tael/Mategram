package com.xxcactussell.presentation.messages.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.xxcactussell.domain.messages.model.MessageAnimatedEmoji
import com.xxcactussell.domain.messages.model.MessageAnimation
import com.xxcactussell.domain.messages.model.MessageDice
import com.xxcactussell.domain.messages.model.MessageDocument
import com.xxcactussell.domain.messages.model.MessagePhoto
import com.xxcactussell.domain.messages.model.MessageSticker
import com.xxcactussell.domain.messages.model.MessageText
import com.xxcactussell.domain.messages.model.MessageVideo
import com.xxcactussell.presentation.messages.model.MessageUiItem
import com.xxcactussell.presentation.messages.model.getReactions
import com.xxcactussell.presentation.messages.model.isOutgoing

@Composable
fun MessageItemContent(
    message: MessageUiItem,
    topCorner: Dp,
    bottomCorner: Dp,
    needSenderName: Boolean,
    needAvatar: Boolean,
    isGroup: Boolean,
    isUnread: Boolean,
    onReplyClicked: (Long) -> Unit,
    onMediaClicked: (Long) -> Unit,
    onEvent: (Any) -> Unit
) {
    when(message) {
        is MessageUiItem.DateSeparator -> {
            DateSeparator(message.timestamp)
        }
        is MessageUiItem.MessageItem -> {
            val content = message.message.content
            if (content !is MessageText
                && content !is MessageSticker
                && content !is MessageAnimatedEmoji
                && content !is MessageAnimation
                && content !is MessagePhoto
                && content !is MessageVideo
                && content !is MessageDocument
            ) {
                SystemServiceMessage(message = message)
            } else {
                val hasBubble = content !is MessageSticker &&
                        content !is MessageDice &&
                        content !is MessageAnimatedEmoji &&
                        !(content is MessageAnimation && content.caption.text.isEmpty()) &&
                        !(content is MessagePhoto && content.caption.text.isEmpty()) &&
                        !(content is MessageVideo && content.caption.text.isEmpty())

                BubbleMessage(
                    message = message,
                    isOutgoing = message.message.isOutgoing,
                    hasBubble = hasBubble,
                    topCorner = topCorner,
                    needSenderName = needSenderName,
                    needAvatar = needAvatar,
                    isGroup = isGroup,
                    bottomCorner = bottomCorner,
                    isUnread = isUnread,
                    onReplyClicked = onReplyClicked
                ) {
                    MessageContent(message = message, onMediaClicked = onMediaClicked, onEvent = onEvent)
                }
            }
        }
        is MessageUiItem.AlbumItem -> {
            val isCaption = message.messages.firstOrNull {
                when (it.message.content) {
                    is MessagePhoto -> (it.message.content as MessagePhoto).caption.text.isNotEmpty()
                    is MessageVideo -> (it.message.content as MessageVideo).caption.text.isNotEmpty()
                    is MessageAnimation -> (it.message.content as MessageAnimation).caption.text.isNotEmpty()
                    else -> false
                }
            }
            BubbleMessage(
                message = message,
                isOutgoing = message.messages.first().message.isOutgoing,
                hasBubble = isCaption != null || message.messages.any { it.message.content is MessageDocument },
                topCorner = topCorner,
                bottomCorner = bottomCorner,
                needAvatar = needAvatar,
                isGroup = isGroup,
                needSenderName = needSenderName,
                isUnread = isUnread,
                onReplyClicked = onReplyClicked
            ) {
                MessageAlbum(messages = message.messages, onMediaClicked = onMediaClicked)
            }
        }
    }
}