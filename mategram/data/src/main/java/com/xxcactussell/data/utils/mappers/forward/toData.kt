package com.xxcactussell.data.utils.mappers.forward

import com.xxcactussell.data.utils.mappers.message.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun ForwardSource.toData(): TdApi.ForwardSource = TdApi.ForwardSource(
    this.chatId,
    this.messageId,
    this.senderId?.toData(),
    this.senderName,
    this.date,
    this.isOutgoing
)

