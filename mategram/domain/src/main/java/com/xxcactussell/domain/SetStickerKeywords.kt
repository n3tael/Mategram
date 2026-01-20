package com.xxcactussell.domain

data class SetStickerKeywords(
    val sticker: InputFile,
    val keywords: List<String>
) : Function
