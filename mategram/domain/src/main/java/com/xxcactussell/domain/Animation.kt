package com.xxcactussell.domain

data class Animation(
    val duration: Int,
    val width: Int,
    val height: Int,
    val fileName: String,
    val mimeType: String,
    val hasStickers: Boolean,
    val minithumbnail: Minithumbnail? = null,
    val thumbnail: Thumbnail? = null,
    val animation: File
) : Object
