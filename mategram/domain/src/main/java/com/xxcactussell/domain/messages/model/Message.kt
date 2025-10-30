package com.xxcactussell.domain.messages.model

import com.xxcactussell.domain.chats.model.ProfilePhoto

sealed interface MessageSender {

    data class User(
        val id: Long
    ) : MessageSender

    data class Chat(
        val id: Long
    ) : MessageSender

    object Unknown: MessageSender
}

fun MessageSender.getId() : Long? {
    return when (this) {
        is MessageSender.User -> this.id
        is MessageSender.Chat -> this.id
        is MessageSender.Unknown -> null
    }
}

data class Error(
    val text: String,
    val code: Int
)

sealed interface MessageStatus {

    data class Pending(
        val id: Int,
    ) : MessageStatus

    data class Failed(
        val error: Error,
        val canRetry: Boolean = true,
        val needAnotherSender: Boolean = false,
        val needAnotherReplyQuote: Boolean = false,
        val needDropReply: Boolean = false,
        val requiredPaidMessageStarCount: Long = 0,
        val retryAfter: Double = 0.0
    ) : MessageStatus

}

data class Message(
    val id: Long,
    val chatId: Long,

    val senderId: MessageSender,
    val sendingState: MessageStatus?,
    val isOutgoing: Boolean,
    val isPinned: Boolean,

    val content: MessageContent,
    val date: Int,
    val editDate: Int,
    val authorSignature: String?,
    val mediaAlbumId: Long,

    val containsUnreadMention: Boolean,
    val isChannelPost: Boolean,
    val canBeSaved: Boolean,

    val messageThreadId: Long,

    val selfDestructIn: Double,
    val autoDeleteIn: Double
)