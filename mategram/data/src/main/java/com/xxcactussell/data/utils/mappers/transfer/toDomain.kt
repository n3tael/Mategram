package com.xxcactussell.data.utils.mappers.transfer

import com.xxcactussell.data.utils.mappers.message.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.TransferBusinessAccountStars.toDomain(): TransferBusinessAccountStars = TransferBusinessAccountStars(
    businessConnectionId = this.businessConnectionId,
    starCount = this.starCount
)

fun TdApi.TransferChatOwnership.toDomain(): TransferChatOwnership = TransferChatOwnership(
    chatId = this.chatId,
    userId = this.userId,
    password = this.password
)

fun TdApi.TransferGift.toDomain(): TransferGift = TransferGift(
    businessConnectionId = this.businessConnectionId,
    receivedGiftId = this.receivedGiftId,
    newOwnerId = this.newOwnerId.toDomain(),
    starCount = this.starCount
)

