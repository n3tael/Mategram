package com.xxcactussell.data.utils.mappers.rtmp

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun RtmpUrl.toData(): TdApi.RtmpUrl = TdApi.RtmpUrl(
    this.url,
    this.streamKey
)

