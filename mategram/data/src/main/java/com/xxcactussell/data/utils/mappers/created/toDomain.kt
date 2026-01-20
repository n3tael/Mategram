package com.xxcactussell.data.utils.mappers.created

import com.xxcactussell.data.utils.mappers.failed.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.CreatedBasicGroupChat.toDomain(): CreatedBasicGroupChat = CreatedBasicGroupChat(
    chatId = this.chatId,
    failedToAddMembers = this.failedToAddMembers.toDomain()
)

