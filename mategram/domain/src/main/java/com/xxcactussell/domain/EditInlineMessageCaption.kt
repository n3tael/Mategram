package com.xxcactussell.domain

data class EditInlineMessageCaption(
    val inlineMessageId: String,
    val replyMarkup: ReplyMarkup,
    val caption: FormattedText,
    val showCaptionAboveMedia: Boolean
) : Function
