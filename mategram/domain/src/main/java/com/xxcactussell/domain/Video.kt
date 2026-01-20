package com.xxcactussell.domain

data class Video(
    val duration: Int,
    val width: Int,
    val height: Int,
    val fileName: String,
    val mimeType: String,
    val hasStickers: Boolean,
    val supportsStreaming: Boolean,
    val minithumbnail: Minithumbnail? = null,
    val thumbnail: Thumbnail? = null,
    val video: File
) : Object
