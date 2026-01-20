package com.xxcactussell.data.utils.mappers.count

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.Count.toDomain(): Count = Count(
    count = this.count
)

