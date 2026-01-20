package com.xxcactussell.data.utils.mappers.create

import com.xxcactussell.data.utils.mappers.input.toDomain
import com.xxcactussell.data.utils.mappers.message.toDomain
import com.xxcactussell.data.utils.mappers.star.toDomain
import com.xxcactussell.data.utils.mappers.sticker.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.CreateBasicGroupChat.toDomain(): CreateBasicGroupChat = CreateBasicGroupChat(
    basicGroupId = this.basicGroupId,
    force = this.force
)

fun TdApi.CreateBusinessChatLink.toDomain(): CreateBusinessChatLink = CreateBusinessChatLink(
    linkInfo = this.linkInfo.toDomain()
)

fun TdApi.CreateChatInviteLink.toDomain(): CreateChatInviteLink = CreateChatInviteLink(
    chatId = this.chatId,
    name = this.name,
    expirationDate = this.expirationDate,
    memberLimit = this.memberLimit,
    createsJoinRequest = this.createsJoinRequest
)

fun TdApi.CreateChatSubscriptionInviteLink.toDomain(): CreateChatSubscriptionInviteLink = CreateChatSubscriptionInviteLink(
    chatId = this.chatId,
    name = this.name,
    subscriptionPricing = this.subscriptionPricing.toDomain()
)

fun TdApi.CreateGiftCollection.toDomain(): CreateGiftCollection = CreateGiftCollection(
    ownerId = this.ownerId.toDomain(),
    name = this.name,
    receivedGiftIds = this.receivedGiftIds.toList()
)

fun TdApi.CreateInvoiceLink.toDomain(): CreateInvoiceLink = CreateInvoiceLink(
    businessConnectionId = this.businessConnectionId,
    invoice = this.invoice.toDomain()
)

fun TdApi.CreateNewSecretChat.toDomain(): CreateNewSecretChat = CreateNewSecretChat(
    userId = this.userId
)

fun TdApi.CreateNewStickerSet.toDomain(): CreateNewStickerSet = CreateNewStickerSet(
    userId = this.userId,
    title = this.title,
    name = this.name,
    stickerType = this.stickerType.toDomain(),
    needsRepainting = this.needsRepainting,
    stickers = this.stickers.map { it.toDomain() },
    source = this.source
)

fun TdApi.CreateStoryAlbum.toDomain(): CreateStoryAlbum = CreateStoryAlbum(
    storyPosterChatId = this.storyPosterChatId,
    name = this.name,
    storyIds = this.storyIds
)

fun TdApi.CreateSupergroupChat.toDomain(): CreateSupergroupChat = CreateSupergroupChat(
    supergroupId = this.supergroupId,
    force = this.force
)

fun TdApi.CreateTemporaryPassword.toDomain(): CreateTemporaryPassword = CreateTemporaryPassword(
    password = this.password,
    validFor = this.validFor
)

fun TdApi.CreateVideoChat.toDomain(): CreateVideoChat = CreateVideoChat(
    chatId = this.chatId,
    title = this.title,
    startDate = this.startDate,
    isRtmpStream = this.isRtmpStream
)

