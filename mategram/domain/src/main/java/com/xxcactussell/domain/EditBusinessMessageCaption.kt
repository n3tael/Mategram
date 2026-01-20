package com.xxcactussell.domain

data class EditBusinessMessageCaption(
    val businessConnectionId: String,
    val chatId: Long,
    val messageId: Long,
    val replyMarkup: ReplyMarkup,
    val caption: FormattedText,
    val showCaptionAboveMedia: Boolean
) : Function
