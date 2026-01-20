package com.xxcactussell.data.utils.mappers.stop

import com.xxcactussell.data.utils.mappers.bots.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun StopBusinessPoll.toData(): TdApi.StopBusinessPoll = TdApi.StopBusinessPoll(
    this.businessConnectionId,
    this.chatId,
    this.messageId,
    this.replyMarkup.toData()
)

fun StopPoll.toData(): TdApi.StopPoll = TdApi.StopPoll(
    this.chatId,
    this.messageId,
    this.replyMarkup.toData()
)

