package com.xxcactussell.domain

data class LinkPreviewTypeEmbeddedAudioPlayer(
    val url: String,
    val thumbnail: Photo? = null,
    val duration: Int,
    val width: Int,
    val height: Int
) : LinkPreviewType
