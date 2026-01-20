package com.xxcactussell.domain

data class InputStoryContentPhoto(
    val photo: InputFile,
    val addedStickerFileIds: IntArray
) : InputStoryContent
