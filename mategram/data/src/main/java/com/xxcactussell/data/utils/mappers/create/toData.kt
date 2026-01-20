package com.xxcactussell.data.utils.mappers.create

import com.xxcactussell.data.utils.mappers.input.toData
import com.xxcactussell.data.utils.mappers.message.toData
import com.xxcactussell.data.utils.mappers.star.toData
import com.xxcactussell.data.utils.mappers.sticker.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun CreateBasicGroupChat.toData(): TdApi.CreateBasicGroupChat = TdApi.CreateBasicGroupChat(
    this.basicGroupId,
    this.force
)

fun CreateBusinessChatLink.toData(): TdApi.CreateBusinessChatLink = TdApi.CreateBusinessChatLink(
    this.linkInfo.toData()
)

fun CreateChatInviteLink.toData(): TdApi.CreateChatInviteLink = TdApi.CreateChatInviteLink(
    this.chatId,
    this.name,
    this.expirationDate,
    this.memberLimit,
    this.createsJoinRequest
)

fun CreateChatSubscriptionInviteLink.toData(): TdApi.CreateChatSubscriptionInviteLink = TdApi.CreateChatSubscriptionInviteLink(
    this.chatId,
    this.name,
    this.subscriptionPricing.toData()
)

fun CreateGiftCollection.toData(): TdApi.CreateGiftCollection = TdApi.CreateGiftCollection(
    this.ownerId.toData(),
    this.name,
    this.receivedGiftIds.toTypedArray()
)

fun CreateInvoiceLink.toData(): TdApi.CreateInvoiceLink = TdApi.CreateInvoiceLink(
    this.businessConnectionId,
    this.invoice.toData()
)

fun CreateNewSecretChat.toData(): TdApi.CreateNewSecretChat = TdApi.CreateNewSecretChat(
    this.userId
)

fun CreateNewStickerSet.toData(): TdApi.CreateNewStickerSet = TdApi.CreateNewStickerSet(
    this.userId,
    this.title,
    this.name,
    this.stickerType.toData(),
    this.needsRepainting,
    this.stickers.map { it.toData() }.toTypedArray(),
    this.source
)

fun CreateStoryAlbum.toData(): TdApi.CreateStoryAlbum = TdApi.CreateStoryAlbum(
    this.storyPosterChatId,
    this.name,
    this.storyIds
)

fun CreateSupergroupChat.toData(): TdApi.CreateSupergroupChat = TdApi.CreateSupergroupChat(
    this.supergroupId,
    this.force
)

fun CreateTemporaryPassword.toData(): TdApi.CreateTemporaryPassword = TdApi.CreateTemporaryPassword(
    this.password,
    this.validFor
)

fun CreateVideoChat.toData(): TdApi.CreateVideoChat = TdApi.CreateVideoChat(
    this.chatId,
    this.title,
    this.startDate,
    this.isRtmpStream
)

