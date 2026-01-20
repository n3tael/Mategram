package com.xxcactussell.data.utils.mappers.http

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun HttpUrl.toData(): TdApi.HttpUrl = TdApi.HttpUrl(
    this.url
)

