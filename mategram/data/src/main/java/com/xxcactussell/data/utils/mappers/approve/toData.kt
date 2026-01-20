package com.xxcactussell.data.utils.mappers.approve

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun ApproveSuggestedPost.toData(): TdApi.ApproveSuggestedPost = TdApi.ApproveSuggestedPost(
    this.chatId,
    this.messageId,
    this.sendDate
)

