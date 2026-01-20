package com.xxcactussell.domain

data class SetStickerSetThumbnail(
    val userId: Long,
    val name: String,
    val thumbnail: InputFile,
    val format: StickerFormat
) : Function
