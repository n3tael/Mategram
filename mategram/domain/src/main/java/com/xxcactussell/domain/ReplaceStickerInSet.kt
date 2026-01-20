package com.xxcactussell.domain

data class ReplaceStickerInSet(
    val userId: Long,
    val name: String,
    val oldSticker: InputFile,
    val newSticker: InputSticker
) : Function
