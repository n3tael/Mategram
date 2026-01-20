package com.xxcactussell.data.utils.mappers.shared

import com.xxcactussell.data.utils.mappers.photo.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.SharedChat.toDomain(): SharedChat = SharedChat(
    chatId = this.chatId,
    title = this.title,
    username = this.username,
    photo = this.photo?.toDomain()
)

fun TdApi.SharedUser.toDomain(): SharedUser = SharedUser(
    userId = this.userId,
    firstName = this.firstName,
    lastName = this.lastName,
    username = this.username,
    photo = this.photo?.toDomain()
)

