package com.xxcactussell.domain

data class EditMessageText(
    val chatId: Long,
    val messageId: Long,
    val replyMarkup: ReplyMarkup,
    val inputMessageContent: InputMessageContent
) : Function
