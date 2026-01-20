package com.xxcactussell.domain

data class MessageThreadInfo(
    val chatId: Long,
    val messageThreadId: Long,
    val replyInfo: MessageReplyInfo? = null,
    val unreadMessageCount: Int,
    val messages: List<Message>,
    val draftMessage: DraftMessage? = null
) : Object
