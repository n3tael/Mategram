package com.xxcactussell.domain

data class EditMessageReplyMarkup(
    val chatId: Long,
    val messageId: Long,
    val replyMarkup: ReplyMarkup
) : Function
