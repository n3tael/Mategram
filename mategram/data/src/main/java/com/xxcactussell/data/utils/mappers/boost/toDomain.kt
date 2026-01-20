package com.xxcactussell.data.utils.mappers.boost

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.BoostChat.toDomain(): BoostChat = BoostChat(
    chatId = this.chatId,
    slotIds = this.slotIds
)

