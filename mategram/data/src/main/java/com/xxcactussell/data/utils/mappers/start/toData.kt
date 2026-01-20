package com.xxcactussell.data.utils.mappers.start

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun StartScheduledVideoChat.toData(): TdApi.StartScheduledVideoChat = TdApi.StartScheduledVideoChat(
    this.groupCallId
)

