package com.xxcactussell.data.utils.mappers.decline

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun DeclineGroupCallInvitation.toData(): TdApi.DeclineGroupCallInvitation = TdApi.DeclineGroupCallInvitation(
    this.chatId,
    this.messageId
)

fun DeclineSuggestedPost.toData(): TdApi.DeclineSuggestedPost = TdApi.DeclineSuggestedPost(
    this.chatId,
    this.messageId,
    this.comment
)

