package com.xxcactussell.presentation.messages.model

import com.xxcactussell.domain.messages.model.Message
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

fun MessageUiItem.getAvatar() : AvatarUiState? {
    return when (this) {
        is MessageUiItem.MessageItem -> this.profilePhoto
        is MessageUiItem.AlbumItem -> this.messages.firstOrNull()?.profilePhoto
        is MessageUiItem.DateSeparator -> null
    }
}