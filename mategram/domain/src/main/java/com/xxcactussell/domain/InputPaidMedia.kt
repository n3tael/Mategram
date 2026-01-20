package com.xxcactussell.domain

data class InputPaidMedia(
    val type: InputPaidMediaType,
    val media: InputFile,
    val thumbnail: InputThumbnail,
    val addedStickerFileIds: IntArray,
    val width: Int,
    val height: Int
) : Object
