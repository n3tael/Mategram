package com.xxcactussell.domain

data class DirectMessagesChatTopic(
    val chatId: Long,
    val id: Long,
    val senderId: MessageSender,
    val order: Long,
    val canSendUnpaidMessages: Boolean,
    val isMarkedAsUnread: Boolean,
    val unreadCount: Long,
    val lastReadInboxMessageId: Long,
    val lastReadOutboxMessageId: Long,
    val unreadReactionCount: Long,
    val lastMessage: Message? = null,
    val draftMessage: DraftMessage? = null
) : Object
