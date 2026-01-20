package com.xxcactussell.domain

data class BlockMessageSenderFromReplies(
    val messageId: Long,
    val deleteMessage: Boolean,
    val deleteAllMessages: Boolean,
    val reportSpam: Boolean
) : Function
