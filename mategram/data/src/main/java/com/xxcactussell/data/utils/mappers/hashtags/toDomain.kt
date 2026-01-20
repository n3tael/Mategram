package com.xxcactussell.data.utils.mappers.hashtags

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.Hashtags.toDomain(): Hashtags = Hashtags(
    hashtags = this.hashtags.toList()
)

