package com.xxcactussell.domain

data class SetStickerEmojis(
    val sticker: InputFile,
    val emojis: String
) : Function
