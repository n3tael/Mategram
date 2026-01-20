package com.xxcactussell.domain

data class EditInlineMessageMedia(
    val inlineMessageId: String,
    val replyMarkup: ReplyMarkup,
    val inputMessageContent: InputMessageContent
) : Function
