package com.xxcactussell.data.utils.mappers.seconds

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun Seconds.toData(): TdApi.Seconds = TdApi.Seconds(
    this.seconds
)

