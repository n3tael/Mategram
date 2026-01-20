package com.xxcactussell.data.utils.mappers.http

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.HttpUrl.toDomain(): HttpUrl = HttpUrl(
    url = this.url
)

