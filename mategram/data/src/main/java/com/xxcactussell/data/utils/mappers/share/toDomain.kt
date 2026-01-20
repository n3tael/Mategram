package com.xxcactussell.data.utils.mappers.share

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.ShareChatWithBot.toDomain(): ShareChatWithBot = ShareChatWithBot(
    chatId = this.chatId,
    messageId = this.messageId,
    buttonId = this.buttonId,
    sharedChatId = this.sharedChatId,
    onlyCheck = this.onlyCheck
)

fun TdApi.SharePhoneNumber.toDomain(): SharePhoneNumber = SharePhoneNumber(
    userId = this.userId
)

fun TdApi.ShareUsersWithBot.toDomain(): ShareUsersWithBot = ShareUsersWithBot(
    chatId = this.chatId,
    messageId = this.messageId,
    buttonId = this.buttonId,
    sharedUserIds = this.sharedUserIds,
    onlyCheck = this.onlyCheck
)

