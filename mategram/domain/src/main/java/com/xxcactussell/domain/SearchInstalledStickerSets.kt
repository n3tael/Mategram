package com.xxcactussell.domain

data class SearchInstalledStickerSets(
    val stickerType: StickerType,
    val query: String,
    val limit: Int
) : Function
