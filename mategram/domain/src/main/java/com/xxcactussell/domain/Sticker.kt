package com.xxcactussell.domain

data class Sticker(
    val id: Long,
    val setId: Long,
    val width: Int,
    val height: Int,
    val emoji: String,
    val format: StickerFormat,
    val fullType: StickerFullType,
    val thumbnail: Thumbnail? = null,
    val sticker: File
) : Object
