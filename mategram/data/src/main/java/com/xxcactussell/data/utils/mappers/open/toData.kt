package com.xxcactussell.data.utils.mappers.open

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun OpenBotSimilarBot.toData(): TdApi.OpenBotSimilarBot = TdApi.OpenBotSimilarBot(
    this.botUserId,
    this.openedBotUserId
)

fun OpenChat.toData(): TdApi.OpenChat = TdApi.OpenChat(
    this.chatId
)

fun OpenChatSimilarChat.toData(): TdApi.OpenChatSimilarChat = TdApi.OpenChatSimilarChat(
    this.chatId,
    this.openedChatId
)

fun OpenMessageContent.toData(): TdApi.OpenMessageContent = TdApi.OpenMessageContent(
    this.chatId,
    this.messageId
)

fun OpenSponsoredChat.toData(): TdApi.OpenSponsoredChat = TdApi.OpenSponsoredChat(
    this.sponsoredChatUniqueId
)

