package com.xxcactussell.domain

data class EditBusinessMessageText(
    val businessConnectionId: String,
    val chatId: Long,
    val messageId: Long,
    val replyMarkup: ReplyMarkup,
    val inputMessageContent: InputMessageContent
) : Function
