package com.xxcactussell.domain

data class PaidMediaVideo(
    val video: Video,
    val cover: Photo? = null,
    val startTimestamp: Int
) : PaidMedia
