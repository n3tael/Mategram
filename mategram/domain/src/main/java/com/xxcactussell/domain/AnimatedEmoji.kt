package com.xxcactussell.domain

data class AnimatedEmoji(
    val sticker: Sticker? = null,
    val stickerWidth: Int,
    val stickerHeight: Int,
    val fitzpatrickType: Int,
    val sound: File? = null
) : Object
