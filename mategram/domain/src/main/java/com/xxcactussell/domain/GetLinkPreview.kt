package com.xxcactussell.domain

data class GetLinkPreview(
    val text: FormattedText,
    val linkPreviewOptions: LinkPreviewOptions
) : Function
