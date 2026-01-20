package com.xxcactussell.domain

data class StoryVideo(
    val duration: Double,
    val width: Int,
    val height: Int,
    val hasStickers: Boolean,
    val isAnimation: Boolean,
    val minithumbnail: Minithumbnail? = null,
    val thumbnail: Thumbnail? = null,
    val preloadPrefixSize: Int,
    val coverFrameTimestamp: Double,
    val video: File
) : Object
