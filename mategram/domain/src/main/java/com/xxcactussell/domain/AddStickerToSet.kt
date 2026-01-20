package com.xxcactussell.domain

data class AddStickerToSet(
    val userId: Long,
    val name: String,
    val sticker: InputSticker
) : Function
