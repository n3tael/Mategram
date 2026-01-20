package com.xxcactussell.domain

data class EditInlineMessageReplyMarkup(
    val inlineMessageId: String,
    val replyMarkup: ReplyMarkup
) : Function
