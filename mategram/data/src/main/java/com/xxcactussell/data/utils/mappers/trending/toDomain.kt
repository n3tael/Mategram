package com.xxcactussell.data.utils.mappers.trending

import com.xxcactussell.data.utils.mappers.sticker.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.TrendingStickerSets.toDomain(): TrendingStickerSets = TrendingStickerSets(
    totalCount = this.totalCount,
    sets = this.sets.map { it.toDomain() },
    isPremium = this.isPremium
)

