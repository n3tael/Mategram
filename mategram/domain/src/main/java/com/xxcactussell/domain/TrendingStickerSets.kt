package com.xxcactussell.domain

data class TrendingStickerSets(
    val totalCount: Int,
    val sets: List<StickerSetInfo>,
    val isPremium: Boolean
) : Object
