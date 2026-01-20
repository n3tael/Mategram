package com.xxcactussell.data.utils.mappers.allow

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.AllowBotToSendMessages.toDomain(): AllowBotToSendMessages = AllowBotToSendMessages(
    botUserId = this.botUserId
)

fun TdApi.AllowUnpaidMessagesFromUser.toDomain(): AllowUnpaidMessagesFromUser = AllowUnpaidMessagesFromUser(
    userId = this.userId,
    refundPayments = this.refundPayments
)

