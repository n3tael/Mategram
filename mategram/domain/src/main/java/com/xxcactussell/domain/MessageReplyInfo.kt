package com.xxcactussell.domain

data class MessageReplyInfo(
    val replyCount: Int,
    val recentReplierIds: List<MessageSender>,
    val lastReadInboxMessageId: Long,
    val lastReadOutboxMessageId: Long,
    val lastMessageId: Long
) : Object
