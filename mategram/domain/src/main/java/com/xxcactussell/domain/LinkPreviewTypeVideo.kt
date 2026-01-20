package com.xxcactussell.domain

data class LinkPreviewTypeVideo(
    val video: Video,
    val cover: Photo? = null,
    val startTimestamp: Int
) : LinkPreviewType
