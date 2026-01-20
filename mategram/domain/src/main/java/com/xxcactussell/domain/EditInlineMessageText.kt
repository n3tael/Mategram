package com.xxcactussell.domain

data class EditInlineMessageText(
    val inlineMessageId: String,
    val replyMarkup: ReplyMarkup,
    val inputMessageContent: InputMessageContent
) : Function
