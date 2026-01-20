package com.xxcactussell.domain

data class CreateNewStickerSet(
    val userId: Long,
    val title: String,
    val name: String,
    val stickerType: StickerType,
    val needsRepainting: Boolean,
    val stickers: List<InputSticker>,
    val source: String
) : Function
