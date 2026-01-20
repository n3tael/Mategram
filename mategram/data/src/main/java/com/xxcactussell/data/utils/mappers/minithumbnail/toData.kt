package com.xxcactussell.data.utils.mappers.minithumbnail

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun Minithumbnail.toData(): TdApi.Minithumbnail = TdApi.Minithumbnail(
    this.width,
    this.height,
    this.data
)

