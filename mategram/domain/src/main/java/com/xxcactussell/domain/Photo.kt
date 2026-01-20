package com.xxcactussell.domain

data class Photo(
    val hasStickers: Boolean,
    val minithumbnail: Minithumbnail? = null,
    val sizes: List<PhotoSize>
) : Object
