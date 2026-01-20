package com.xxcactussell.data.utils.mappers.custom

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.CustomRequestResult.toDomain(): CustomRequestResult = CustomRequestResult(
    result = this.result
)

