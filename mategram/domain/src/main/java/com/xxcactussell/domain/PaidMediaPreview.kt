package com.xxcactussell.domain

data class PaidMediaPreview(
    val width: Int,
    val height: Int,
    val duration: Int,
    val minithumbnail: Minithumbnail? = null
) : PaidMedia
