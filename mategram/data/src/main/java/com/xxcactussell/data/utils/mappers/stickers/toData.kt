package com.xxcactussell.data.utils.mappers.stickers

import com.xxcactussell.data.utils.mappers.sticker.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun Stickers.toData(): TdApi.Stickers = TdApi.Stickers(
    this.stickers.map { it.toData() }.toTypedArray()
)

