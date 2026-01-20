package com.xxcactussell.data.utils.mappers.created

import com.xxcactussell.data.utils.mappers.failed.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun CreatedBasicGroupChat.toData(): TdApi.CreatedBasicGroupChat = TdApi.CreatedBasicGroupChat(
    this.chatId,
    this.failedToAddMembers.toData()
)

