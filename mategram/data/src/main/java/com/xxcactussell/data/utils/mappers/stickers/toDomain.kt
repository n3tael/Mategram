package com.xxcactussell.data.utils.mappers.stickers

import com.xxcactussell.data.utils.mappers.sticker.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.Stickers.toDomain(): Stickers = Stickers(
    stickers = this.stickers.map { it.toDomain() }
)

