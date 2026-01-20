package com.xxcactussell.domain

data class GetStickers(
    val stickerType: StickerType,
    val query: String,
    val limit: Int,
    val chatId: Long
) : Function
