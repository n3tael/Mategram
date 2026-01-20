package com.xxcactussell.data.utils.mappers.count

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun Count.toData(): TdApi.Count = TdApi.Count(
    this.count
)

