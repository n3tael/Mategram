package com.xxcactussell.domain

data class LinkPreviewTypeExternalVideo(
    val url: String,
    val mimeType: String,
    val width: Int,
    val height: Int,
    val duration: Int
) : LinkPreviewType
