package com.xxcactussell.data.utils.mappers.start

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.StartScheduledVideoChat.toDomain(): StartScheduledVideoChat = StartScheduledVideoChat(
    groupCallId = this.groupCallId
)

