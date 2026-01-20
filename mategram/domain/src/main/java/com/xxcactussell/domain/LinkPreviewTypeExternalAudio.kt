package com.xxcactussell.domain

data class LinkPreviewTypeExternalAudio(
    val url: String,
    val mimeType: String,
    val duration: Int
) : LinkPreviewType
