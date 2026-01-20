package com.xxcactussell.data.utils.mappers.data

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.Data.toDomain(): Data = Data(
    data = this.data
)

