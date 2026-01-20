package com.xxcactussell.domain

data class SetStickerMaskPosition(
    val sticker: InputFile,
    val maskPosition: MaskPosition
) : Function
