package com.xxcactussell.data.utils.mappers.allow

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun AllowBotToSendMessages.toData(): TdApi.AllowBotToSendMessages = TdApi.AllowBotToSendMessages(
    this.botUserId
)

fun AllowUnpaidMessagesFromUser.toData(): TdApi.AllowUnpaidMessagesFromUser = TdApi.AllowUnpaidMessagesFromUser(
    this.userId,
    this.refundPayments
)

