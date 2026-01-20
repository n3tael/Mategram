package com.xxcactussell.domain

data class InputMessageText(
    val text: FormattedText,
    val linkPreviewOptions: LinkPreviewOptions? = null,
    val clearDraft: Boolean
) : InputMessageContent
