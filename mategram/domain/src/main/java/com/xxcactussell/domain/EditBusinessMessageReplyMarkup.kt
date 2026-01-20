package com.xxcactussell.domain

data class EditBusinessMessageReplyMarkup(
    val businessConnectionId: String,
    val chatId: Long,
    val messageId: Long,
    val replyMarkup: ReplyMarkup
) : Function
