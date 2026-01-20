package com.xxcactussell.domain

data class InputStoryContentVideo(
    val video: InputFile,
    val addedStickerFileIds: IntArray,
    val duration: Double,
    val coverFrameTimestamp: Double,
    val isAnimation: Boolean
) : InputStoryContent
