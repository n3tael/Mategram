package com.xxcactussell.domain

data class GetStickerOutline(
    val stickerFileId: Int,
    val forAnimatedEmoji: Boolean,
    val forClickedAnimatedEmojiMessage: Boolean
) : Function
