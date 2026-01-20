package com.xxcactussell.domain

data class GetTrendingStickerSets(
    val stickerType: StickerType,
    val offset: Int,
    val limit: Int
) : Function
