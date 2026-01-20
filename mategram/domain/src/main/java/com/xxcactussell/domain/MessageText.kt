package com.xxcactussell.domain

data class MessageText(
    val text: FormattedText,
    val linkPreview: LinkPreview? = null,
    val linkPreviewOptions: LinkPreviewOptions? = null
) : MessageContent
