package com.xxcactussell.data.utils.mappers.boost

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun BoostChat.toData(): TdApi.BoostChat = TdApi.BoostChat(
    this.chatId,
    this.slotIds
)

