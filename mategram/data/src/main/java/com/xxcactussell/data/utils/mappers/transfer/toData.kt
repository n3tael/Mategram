package com.xxcactussell.data.utils.mappers.transfer

import com.xxcactussell.data.utils.mappers.message.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun TransferBusinessAccountStars.toData(): TdApi.TransferBusinessAccountStars = TdApi.TransferBusinessAccountStars(
    this.businessConnectionId,
    this.starCount
)

fun TransferChatOwnership.toData(): TdApi.TransferChatOwnership = TdApi.TransferChatOwnership(
    this.chatId,
    this.userId,
    this.password
)

fun TransferGift.toData(): TdApi.TransferGift = TdApi.TransferGift(
    this.businessConnectionId,
    this.receivedGiftId,
    this.newOwnerId.toData(),
    this.starCount
)

