package com.xxcactussell.domain

data class InputMessageSticker(
    val sticker: InputFile,
    val thumbnail: InputThumbnail,
    val width: Int,
    val height: Int,
    val emoji: String
) : InputMessageContent
