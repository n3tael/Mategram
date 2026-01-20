package com.xxcactussell.domain

data class EditMessageMedia(
    val chatId: Long,
    val messageId: Long,
    val replyMarkup: ReplyMarkup,
    val inputMessageContent: InputMessageContent
) : Function
