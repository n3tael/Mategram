package com.xxcactussell.data.utils.mappers.forward

import com.xxcactussell.data.utils.mappers.message.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.ForwardSource.toDomain(): ForwardSource = ForwardSource(
    chatId = this.chatId,
    messageId = this.messageId,
    senderId = this.senderId?.toDomain(),
    senderName = this.senderName,
    date = this.date,
    isOutgoing = this.isOutgoing
)

