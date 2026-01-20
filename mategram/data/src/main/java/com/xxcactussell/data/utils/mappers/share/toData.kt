package com.xxcactussell.data.utils.mappers.share

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun ShareChatWithBot.toData(): TdApi.ShareChatWithBot = TdApi.ShareChatWithBot(
    this.chatId,
    this.messageId,
    this.buttonId,
    this.sharedChatId,
    this.onlyCheck
)

fun SharePhoneNumber.toData(): TdApi.SharePhoneNumber = TdApi.SharePhoneNumber(
    this.userId
)

fun ShareUsersWithBot.toData(): TdApi.ShareUsersWithBot = TdApi.ShareUsersWithBot(
    this.chatId,
    this.messageId,
    this.buttonId,
    this.sharedUserIds,
    this.onlyCheck
)

