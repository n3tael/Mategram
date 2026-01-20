package com.xxcactussell.domain

data class SearchStickers(
    val stickerType: StickerType,
    val emojis: String,
    val query: String,
    val inputLanguageCodes: List<String>,
    val offset: Int,
    val limit: Int
) : Function
