package com.xxcactussell.data.utils.mappers.seconds

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.Seconds.toDomain(): Seconds = Seconds(
    seconds = this.seconds
)

