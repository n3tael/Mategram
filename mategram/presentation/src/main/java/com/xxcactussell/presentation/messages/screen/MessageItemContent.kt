package com.xxcactussell.presentation.messages.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import com.xxcactussell.domain.messages.model.MessageContent
import com.xxcactussell.domain.messages.model.MessageContent.SystemService
import com.xxcactussell.presentation.messages.model.MessageUiItem

@Composable
fun MessageItemContent(
    message: MessageUiItem,
    topCorner: Dp,
    bottomCorner: Dp,
    needSenderName: Boolean,
    needAvatar: Boolean,
    isGroup: Boolean,
    isUnread: Boolean,
    onMediaClicked: (Long) -> Unit
) {
    when(message) {
        is MessageUiItem.DateSeparator -> {
            DateSeparator(message.timestamp)
        }
        is MessageUiItem.MessageItem -> {
            val content = message.message.content
            if (content is SystemService) {
                SystemServiceMessage(message = message)
            } else {
                val hasBubble = content !is MessageContent.MessageSticker &&
                        content !is MessageContent.MessageDice &&
                        content !is MessageContent.MessageAnimatedEmoji &&
                        !(content is MessageContent.MessagePhoto && content.caption.text.isEmpty()) &&
                        !(content is MessageContent.MessageVideo && content.caption.text.isEmpty())

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
                ) {
                    MessageContent(message = message, onMediaClicked = onMediaClicked)
                }
            }
        }
        is MessageUiItem.AlbumItem -> {
            val isCaption = message.messages.firstOrNull {
                when (it.message.content) {
                    is MessageContent.MessagePhoto -> (it.message.content as MessageContent.MessagePhoto).caption.text.isNotEmpty()
                    is MessageContent.MessageVideo -> (it.message.content as MessageContent.MessageVideo).caption.text.isNotEmpty()
                    else -> false
                }
            }
            BubbleMessage(
                message = message,
                isOutgoing = message.messages.first().message.isOutgoing,
                hasBubble = isCaption != null,
                topCorner = topCorner,
                bottomCorner = bottomCorner,
                needAvatar = needAvatar,
                isGroup = isGroup,
                needSenderName = needSenderName,
                isUnread = isUnread
            ) {
                MessageAlbum(messages = message.messages, onMediaClicked = onMediaClicked)
            }
        }
    }
}