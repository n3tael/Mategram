package com.xxcactussell.data.utils.mappers.custom

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun CustomRequestResult.toData(): TdApi.CustomRequestResult = TdApi.CustomRequestResult(
    this.result
)

