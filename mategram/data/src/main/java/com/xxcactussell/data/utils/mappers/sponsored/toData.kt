package com.xxcactussell.data.utils.mappers.sponsored

import com.xxcactussell.data.utils.mappers.advertisement.toData
import com.xxcactussell.data.utils.mappers.message.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun SponsoredChat.toData(): TdApi.SponsoredChat = TdApi.SponsoredChat(
    this.uniqueId,
    this.chatId,
    this.sponsorInfo,
    this.additionalInfo
)

fun SponsoredChats.toData(): TdApi.SponsoredChats = TdApi.SponsoredChats(
    this.chats.map { it.toData() }.toTypedArray()
)

fun SponsoredMessage.toData(): TdApi.SponsoredMessage = TdApi.SponsoredMessage(
    this.messageId,
    this.isRecommended,
    this.canBeReported,
    this.content.toData(),
    this.sponsor.toData(),
    this.title,
    this.buttonText,
    this.accentColorId,
    this.backgroundCustomEmojiId,
    this.additionalInfo
)

fun SponsoredMessages.toData(): TdApi.SponsoredMessages = TdApi.SponsoredMessages(
    this.messages.map { it.toData() }.toTypedArray(),
    this.messagesBetween
)

