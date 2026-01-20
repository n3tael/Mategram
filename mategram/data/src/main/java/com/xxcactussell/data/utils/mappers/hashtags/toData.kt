package com.xxcactussell.data.utils.mappers.hashtags

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun Hashtags.toData(): TdApi.Hashtags = TdApi.Hashtags(
    this.hashtags.toTypedArray()
)

