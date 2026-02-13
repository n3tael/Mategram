package com.xxcactussell.domain

data class InputMessageDocument(
    val document: InputFile,
    val thumbnail: InputThumbnail?,
    val disableContentTypeDetection: Boolean,
    val caption: FormattedText
) : InputMessageContent
