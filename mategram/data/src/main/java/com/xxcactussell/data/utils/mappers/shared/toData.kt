package com.xxcactussell.data.utils.mappers.shared

import com.xxcactussell.data.utils.mappers.photo.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun SharedChat.toData(): TdApi.SharedChat = TdApi.SharedChat(
    this.chatId,
    this.title,
    this.username,
    this.photo?.toData()
)

fun SharedUser.toData(): TdApi.SharedUser = TdApi.SharedUser(
    this.userId,
    this.firstName,
    this.lastName,
    this.username,
    this.photo?.toData()
)

