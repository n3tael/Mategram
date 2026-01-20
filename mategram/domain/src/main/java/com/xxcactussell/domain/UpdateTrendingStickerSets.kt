package com.xxcactussell.domain

data class UpdateTrendingStickerSets(
    val stickerType: StickerType,
    val stickerSets: TrendingStickerSets
) : Update
