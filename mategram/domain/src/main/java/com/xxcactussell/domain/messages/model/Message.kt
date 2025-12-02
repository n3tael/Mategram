package com.xxcactussell.domain.messages.model

fun MessageSender.getId() : Long? {
    return when (this) {
        is MessageSenderChat -> this.chatId
        is MessageSenderUser -> this.userId
    }
}

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
    val autoDeleteIn: Double,

    val forwardInfo: MessageForwardInfo? = null,
    val importInfo: MessageImportInfo? = null,
    val interactionInfo: MessageInteractionInfo? = null,
    val replyTo: MessageReplyTo? = null,
)

data class MessageForwardInfo(
    val origin: MessageOrigin,
    val date: Int,
    val source: ForwardSource ? = null
)

sealed interface MessageOrigin {
    data class User(val userId: Long) : MessageOrigin
    data class Chat(val chatId: Long) : MessageOrigin
    data class HiddenUser(val name: String) : MessageOrigin
    data class Channel(val chatId: Long, val messageId: Long) : MessageOrigin
}

data class ForwardSource(
    val chatId: Long,
    val messageId: Long,
    val sender: MessageSender? = null,
    val senderName: String,
    val date: Int,
    val isOutgoing: Boolean
)

data class MessageImportInfo(
    val senderName: String,
    val date: Int
)

data class MessageInteractionInfo(
    val viewCount: Int,
    val forwardCount: Int,
    val replyInfo: MessageReplyInfo? = null,
    val reactions: MessageReactions? = null
)

data class MessageReplyInfo(
    val replyCount: Int,
    val recentRepliersIds: List<MessageSender> = emptyList(),
    val lastReadInboxMessageId: Long,
    val lastReadOutboxMessageId: Long,
    val lastMessageId: Long
)

data class MessageReactions(
    val reactions: List<MessageReaction> = emptyList(),
    val areTags: Boolean,
    val paidReactors: List<PaidReactor> = emptyList(),
    val canGetAddedReactions: Boolean
)

data class MessageReaction(
    val type: ReactionType,
    val totalCount: Int,
    val isChosen: Boolean,
    val userSenderId: MessageSender? = null,
    val recentSenderIds: List<MessageSender> = emptyList()
)

sealed interface ReactionType {
    data class Emoji(val emoji: String) : ReactionType
    data class Custom(val id: Long) : ReactionType
    object Paid : ReactionType
}

data class PaidReactor(
    val senderId: MessageSender? = null,
    val starCount: Int,
    val isTop: Boolean,
    val isMe: Boolean,
    val isAnonymous: Boolean
)

sealed interface MessageReplyTo {
    data class Message(
        val message: com.xxcactussell.domain.messages.model.Message? = null,
        val chatId: Long,
        val messageId: Long,
        val quote: TextQuote? = null,
        val checkListTaskId: Int,
        val origin: MessageOrigin? = null,
        val originSentDate: Int,
        val content: MessageContent? = null
    ) : MessageReplyTo

    data class Story(
        val storyId: Int,
        val storyPosterChatId: Long
    ) : MessageReplyTo
}

data class TextQuote(
    val text: FormattedText,
    val position: Int,
    val isManual: Boolean
)