package com.xxcactussell.data.utils.mappers.trending

import com.xxcactussell.data.utils.mappers.sticker.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun TrendingStickerSets.toData(): TdApi.TrendingStickerSets = TdApi.TrendingStickerSets(
    this.totalCount,
    this.sets.map { it.toData() }.toTypedArray(),
    this.isPremium
)

