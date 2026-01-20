package com.xxcactussell.data.utils.mappers.decline

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.DeclineGroupCallInvitation.toDomain(): DeclineGroupCallInvitation = DeclineGroupCallInvitation(
    chatId = this.chatId,
    messageId = this.messageId
)

fun TdApi.DeclineSuggestedPost.toDomain(): DeclineSuggestedPost = DeclineSuggestedPost(
    chatId = this.chatId,
    messageId = this.messageId,
    comment = this.comment
)

