package com.xxcactussell.data.utils.mappers.rtmp

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.RtmpUrl.toDomain(): RtmpUrl = RtmpUrl(
    url = this.url,
    streamKey = this.streamKey
)

