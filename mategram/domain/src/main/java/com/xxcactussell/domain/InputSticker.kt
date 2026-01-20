package com.xxcactussell.domain

data class InputSticker(
    val sticker: InputFile,
    val format: StickerFormat,
    val emojis: String,
    val maskPosition: MaskPosition,
    val keywords: List<String>
) : Object
