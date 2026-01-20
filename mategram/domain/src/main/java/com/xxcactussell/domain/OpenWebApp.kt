package com.xxcactussell.domain

data class OpenWebApp(
    val chatId: Long,
    val botUserId: Long,
    val url: String,
    val messageThreadId: Long,
    val directMessagesChatTopicId: Long,
    val replyTo: InputMessageReplyTo,
    val parameters: WebAppOpenParameters
) : Function
