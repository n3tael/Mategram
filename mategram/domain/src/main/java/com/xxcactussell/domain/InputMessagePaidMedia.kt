package com.xxcactussell.domain

data class InputMessagePaidMedia(
    val starCount: Long,
    val paidMedia: List<InputPaidMedia>,
    val caption: FormattedText,
    val showCaptionAboveMedia: Boolean,
    val payload: String
) : InputMessageContent
