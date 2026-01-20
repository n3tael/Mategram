package com.xxcactussell.domain

data class InternalLinkTypeStickerSet(
    val stickerSetName: String,
    val expectCustomEmoji: Boolean
) : InternalLinkType
