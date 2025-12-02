package com.xxcactussell.domain.messages.model

sealed interface MessageListItem {
    data class MessageItem(val message: Message, val sender: Any?) : MessageListItem
    data class AlbumItem(val messages: List<Message>, val sender: Any?) : MessageListItem
}

fun MessageListItem.getDate(): Int {
    return when(this) {
        is MessageListItem.MessageItem -> this.message.date
        is MessageListItem.AlbumItem -> this.messages.first().date
    }
}