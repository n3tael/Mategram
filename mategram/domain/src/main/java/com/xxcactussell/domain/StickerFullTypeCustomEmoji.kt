package com.xxcactussell.domain

data class StickerFullTypeCustomEmoji(
    val customEmojiId: Long,
    val needsRepainting: Boolean
) : StickerFullType
