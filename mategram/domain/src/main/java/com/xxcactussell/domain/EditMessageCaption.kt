package com.xxcactussell.domain

data class EditMessageCaption(
    val chatId: Long,
    val messageId: Long,
    val replyMarkup: ReplyMarkup,
    val caption: FormattedText,
    val showCaptionAboveMedia: Boolean
) : Function
