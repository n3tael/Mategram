package com.xxcactussell.data.utils.mappers.data

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun Data.toData(): TdApi.Data = TdApi.Data(
    this.data
)

