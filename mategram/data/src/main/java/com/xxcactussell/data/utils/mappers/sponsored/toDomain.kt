package com.xxcactussell.data.utils.mappers.sponsored

import com.xxcactussell.data.utils.mappers.advertisement.toDomain
import com.xxcactussell.data.utils.mappers.message.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.SponsoredChat.toDomain(): SponsoredChat = SponsoredChat(
    uniqueId = this.uniqueId,
    chatId = this.chatId,
    sponsorInfo = this.sponsorInfo,
    additionalInfo = this.additionalInfo
)

fun TdApi.SponsoredChats.toDomain(): SponsoredChats = SponsoredChats(
    chats = this.chats.map { it.toDomain() }
)

fun TdApi.SponsoredMessage.toDomain(): SponsoredMessage = SponsoredMessage(
    messageId = this.messageId,
    isRecommended = this.isRecommended,
    canBeReported = this.canBeReported,
    content = this.content.toDomain(),
    sponsor = this.sponsor.toDomain(),
    title = this.title,
    buttonText = this.buttonText,
    accentColorId = this.accentColorId,
    backgroundCustomEmojiId = this.backgroundCustomEmojiId,
    additionalInfo = this.additionalInfo
)

fun TdApi.SponsoredMessages.toDomain(): SponsoredMessages = SponsoredMessages(
    messages = this.messages.map { it.toDomain() },
    messagesBetween = this.messagesBetween
)

