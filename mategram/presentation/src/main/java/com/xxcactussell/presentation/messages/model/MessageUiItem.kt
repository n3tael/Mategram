package com.xxcactussell.presentation.messages.model

import com.xxcactussell.domain.messages.model.Message
import com.xxcactussell.domain.messages.model.MessageAnimatedEmoji
import com.xxcactussell.domain.messages.model.MessageAnimation
import com.xxcactussell.domain.messages.model.MessageAudio
import com.xxcactussell.domain.messages.model.MessageChecklist
import com.xxcactussell.domain.messages.model.MessageContact
import com.xxcactussell.domain.messages.model.MessageDice
import com.xxcactussell.domain.messages.model.MessageDocument
import com.xxcactussell.domain.messages.model.MessageExpiredPhoto
import com.xxcactussell.domain.messages.model.MessageExpiredVideo
import com.xxcactussell.domain.messages.model.MessageExpiredVideoNote
import com.xxcactussell.domain.messages.model.MessageExpiredVoiceNote
import com.xxcactussell.domain.messages.model.MessageGame
import com.xxcactussell.domain.messages.model.MessageGameScore
import com.xxcactussell.domain.messages.model.MessageGift
import com.xxcactussell.domain.messages.model.MessageInvoice
import com.xxcactussell.domain.messages.model.MessageLocation
import com.xxcactussell.domain.messages.model.MessagePaidMedia
import com.xxcactussell.domain.messages.model.MessagePhoto
import com.xxcactussell.domain.messages.model.MessagePoll
import com.xxcactussell.domain.messages.model.MessageReaction
import com.xxcactussell.domain.messages.model.MessageReplyTo
import com.xxcactussell.domain.messages.model.MessageSticker
import com.xxcactussell.domain.messages.model.MessageStory
import com.xxcactussell.domain.messages.model.MessageText
import com.xxcactussell.domain.messages.model.MessageVenue
import com.xxcactussell.domain.messages.model.MessageVideo
import com.xxcactussell.domain.messages.model.MessageVideoNote
import com.xxcactussell.domain.messages.model.MessageVoiceNote
import com.xxcactussell.domain.messages.model.getId
import com.xxcactussell.presentation.chats.model.AvatarUiState

sealed interface MessageUiItem {
    val key: Any

    data class DateSeparator(val timestamp: Int) : MessageUiItem {
        override val key: Any = "date_separator_$timestamp"
    }
    data class MessageItem(val message: Message, val profilePhoto: AvatarUiState?) : MessageUiItem {
        override val key: Any = "message_${message.id}"
    }
    data class AlbumItem(val messages: List<MessageItem>) : MessageUiItem {
        override val key: Any = "album_${messages.first().message.mediaAlbumId}"
    }
}

fun MessageUiItem.getAlbumId(): Long? = when(this) {
    is MessageUiItem.MessageItem -> this.message.mediaAlbumId.takeIf { it != 0L }
    is MessageUiItem.AlbumItem -> this.messages.firstOrNull()?.message?.mediaAlbumId?.takeIf { it != 0L }
    else -> null
}

fun MessageUiItem.isAlbum(): Boolean = this is MessageUiItem.AlbumItem || this is MessageUiItem.MessageItem && this.message.mediaAlbumId != 0L


fun MessageUiItem.getMessage(): Message? {
    return when (this) {
        is MessageUiItem.MessageItem -> this.message
        is MessageUiItem.AlbumItem -> this.messages.firstOrNull()?.message
        is MessageUiItem.DateSeparator -> null
    }
}

fun MessageUiItem.getItem(): MessageUiItem? {
    return when (this) {
        is MessageUiItem.MessageItem -> this
        is MessageUiItem.AlbumItem -> this.messages.firstOrNull()
        is MessageUiItem.DateSeparator -> null
    }
}


fun MessageUiItem.getMessageId(): Long? {
    return when (this) {
        is MessageUiItem.MessageItem -> this.message.id
        is MessageUiItem.AlbumItem -> this.messages.firstOrNull()?.message?.id
        is MessageUiItem.DateSeparator -> null
    }
}

fun MessageUiItem.getMessageDate(): Int? {
    return when (this) {
        is MessageUiItem.MessageItem -> this.message.date
        is MessageUiItem.AlbumItem -> this.messages.firstOrNull()?.message?.date
        is MessageUiItem.DateSeparator -> this.timestamp
    }
}

fun MessageUiItem.isOutgoing(): Boolean? {
    return when (this) {
        is MessageUiItem.MessageItem -> this.message.isOutgoing
        is MessageUiItem.AlbumItem -> this.messages.firstOrNull()?.message?.isOutgoing
        is MessageUiItem.DateSeparator -> null
    }
}

fun MessageUiItem.getMessageSenderId() : Long? {
    return when (this) {
        is MessageUiItem.MessageItem -> this.message.senderId.getId()
        is MessageUiItem.AlbumItem -> this.messages.firstOrNull()?.message?.senderId?.getId()
        is MessageUiItem.DateSeparator -> null
    }
}

fun MessageUiItem.getReplyTo() : MessageReplyTo? {
    return when (this) {
        is MessageUiItem.MessageItem -> if (this.message.replyTo != null) this.message.replyTo else null
        is MessageUiItem.AlbumItem -> this.messages.firstOrNull { it.message.replyTo != null }?.message?.replyTo
        else -> null
    }
}

fun MessageUiItem.getReactions() : List<MessageReaction> {
    return when (this) {
        is MessageUiItem.AlbumItem -> {
            val reactions = emptyList<MessageReaction>().toMutableList()
            this.messages.forEach {
                reactions.addAll(it.message.interactionInfo?.reactions?.reactions ?: emptyList())
            }
            reactions.toList()
        }
        is MessageUiItem.DateSeparator -> emptyList()
        is MessageUiItem.MessageItem -> this.message.interactionInfo?.reactions?.reactions ?: emptyList()
    }
}

fun MessageUiItem.getAvatar() : AvatarUiState? {
    return when (this) {
        is MessageUiItem.MessageItem -> this.profilePhoto
        is MessageUiItem.AlbumItem -> this.messages.firstOrNull()?.profilePhoto
        is MessageUiItem.DateSeparator -> null
    }
}

fun MessageUiItem.getChatId() : Long {
    return when(this) {
        is MessageUiItem.MessageItem -> this.message.chatId
        is MessageUiItem.AlbumItem -> this.messages.first().message.chatId
        is MessageUiItem.DateSeparator -> 0L
    }
}

fun MessageUiItem.isServiceMessage() : Boolean {
    return when (this) {
        is MessageUiItem.AlbumItem -> false
        is MessageUiItem.DateSeparator -> true
        is MessageUiItem.MessageItem -> {
            when(this.message.content) {
                is MessageAnimation,
                is MessageAnimatedEmoji,
                is MessageAudio,
                is MessageChecklist,
                is MessageContact,
                is MessageDice,
                is MessageDocument,
                MessageExpiredPhoto,
                MessageExpiredVideo,
                MessageExpiredVideoNote,
                MessageExpiredVoiceNote,
                is MessageGame,
                is MessageGameScore,
                is MessageGift,
                is MessageInvoice,
                is MessageLocation,
                is MessagePaidMedia,
                is MessagePhoto,
                is MessagePoll,
                is MessageSticker,
                is MessageStory,
                is MessageText,
                is MessageVenue,
                is MessageVideo,
                is MessageVideoNote,
                is MessageVoiceNote -> false
                else -> true
            }
        }
    }
}