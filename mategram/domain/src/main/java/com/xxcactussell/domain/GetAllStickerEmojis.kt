package com.xxcactussell.domain

data class GetAllStickerEmojis(
    val stickerType: StickerType,
    val query: String,
    val chatId: Long,
    val returnOnlyMainEmoji: Boolean
) : Function
