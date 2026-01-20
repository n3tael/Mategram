package com.xxcactussell.data.utils.mappers.animated

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*
import com.xxcactussell.data.utils.mappers.file.toData
// Mappers: Domain -> TdApi

fun AnimatedChatPhoto.toData(): TdApi.AnimatedChatPhoto = TdApi.AnimatedChatPhoto(
    this.length,
    this.file.toData(),
    this.mainFrameTimestamp
)

