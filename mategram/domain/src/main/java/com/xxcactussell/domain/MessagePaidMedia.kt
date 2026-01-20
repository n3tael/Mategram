package com.xxcactussell.domain

data class MessagePaidMedia(
    val starCount: Long,
    val media: List<PaidMedia>,
    val caption: FormattedText,
    val showCaptionAboveMedia: Boolean
) : MessageContent
