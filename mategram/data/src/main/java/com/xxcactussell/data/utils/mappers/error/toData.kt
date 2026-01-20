package com.xxcactussell.data.utils.mappers.error

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun Error.toData(): TdApi.Error = TdApi.Error(
    this.code,
    this.message
)

