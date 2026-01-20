package com.xxcactussell.data.utils.mappers.error

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.Error.toDomain(): Error = Error(
    code = this.code,
    message = this.message
)

