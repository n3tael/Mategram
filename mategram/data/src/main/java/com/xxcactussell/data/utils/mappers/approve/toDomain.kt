package com.xxcactussell.data.utils.mappers.approve

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.ApproveSuggestedPost.toDomain(): ApproveSuggestedPost = ApproveSuggestedPost(
    chatId = this.chatId,
    messageId = this.messageId,
    sendDate = this.sendDate
)

