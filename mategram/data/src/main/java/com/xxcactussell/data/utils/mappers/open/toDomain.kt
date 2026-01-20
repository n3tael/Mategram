package com.xxcactussell.data.utils.mappers.open

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.OpenBotSimilarBot.toDomain(): OpenBotSimilarBot = OpenBotSimilarBot(
    botUserId = this.botUserId,
    openedBotUserId = this.openedBotUserId
)

fun TdApi.OpenChat.toDomain(): OpenChat = OpenChat(
    chatId = this.chatId
)

fun TdApi.OpenChatSimilarChat.toDomain(): OpenChatSimilarChat = OpenChatSimilarChat(
    chatId = this.chatId,
    openedChatId = this.openedChatId
)

fun TdApi.OpenMessageContent.toDomain(): OpenMessageContent = OpenMessageContent(
    chatId = this.chatId,
    messageId = this.messageId
)

fun TdApi.OpenSponsoredChat.toDomain(): OpenSponsoredChat = OpenSponsoredChat(
    sponsoredChatUniqueId = this.sponsoredChatUniqueId
)

