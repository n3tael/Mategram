package com.xxcactussell.domain

data class SendMessage(
    val chatId: Long,
    val messageThreadId: Long,
    val replyTo: InputMessageReplyTo,
    val options: MessageSendOptions,
    val replyMarkup: ReplyMarkup,
    val inputMessageContent: InputMessageContent
) : Function
