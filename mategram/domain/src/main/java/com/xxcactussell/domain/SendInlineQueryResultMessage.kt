package com.xxcactussell.domain

data class SendInlineQueryResultMessage(
    val chatId: Long,
    val messageThreadId: Long,
    val replyTo: InputMessageReplyTo,
    val options: MessageSendOptions,
    val queryId: Long,
    val resultId: String,
    val hideViaBot: Boolean
) : Function
