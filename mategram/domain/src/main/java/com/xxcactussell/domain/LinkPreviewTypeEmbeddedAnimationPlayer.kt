package com.xxcactussell.domain

data class LinkPreviewTypeEmbeddedAnimationPlayer(
    val url: String,
    val thumbnail: Photo? = null,
    val duration: Int,
    val width: Int,
    val height: Int
) : LinkPreviewType
