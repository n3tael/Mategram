package com.xxcactussell.data.utils.mappers.animated

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*
import com.xxcactussell.data.utils.mappers.file.toDomain

// Mappers: TdApi -> Domain

fun TdApi.AnimatedChatPhoto.toDomain(): AnimatedChatPhoto = AnimatedChatPhoto(
    length = this.length,
    file = this.file.toDomain(),
    mainFrameTimestamp = this.mainFrameTimestamp
)

