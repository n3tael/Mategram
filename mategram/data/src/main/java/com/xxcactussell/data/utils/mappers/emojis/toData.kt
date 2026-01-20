package com.xxcactussell.data.utils.mappers.emojis

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun Emojis.toData(): TdApi.Emojis = TdApi.Emojis(
    this.emojis.toTypedArray()
)

