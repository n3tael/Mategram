package com.xxcactussell.data.utils.mappers.stop

import com.xxcactussell.data.utils.mappers.bots.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.StopBusinessPoll.toDomain(): StopBusinessPoll = StopBusinessPoll(
    businessConnectionId = this.businessConnectionId,
    chatId = this.chatId,
    messageId = this.messageId,
    replyMarkup = this.replyMarkup.toDomain()
)

fun TdApi.StopPoll.toDomain(): StopPoll = StopPoll(
    chatId = this.chatId,
    messageId = this.messageId,
    replyMarkup = this.replyMarkup.toDomain()
)

