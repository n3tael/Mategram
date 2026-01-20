package com.xxcactussell.domain

data class MessagePhoto(
    val photo: Photo,
    val caption: FormattedText,
    val showCaptionAboveMedia: Boolean,
    val hasSpoiler: Boolean,
    val isSecret: Boolean
) : MessageContent
