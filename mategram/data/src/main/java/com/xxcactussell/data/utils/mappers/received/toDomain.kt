package com.xxcactussell.data.utils.mappers.received

import com.xxcactussell.data.utils.mappers.formatted.toDomain
import com.xxcactussell.data.utils.mappers.message.toDomain
import com.xxcactussell.data.utils.mappers.sent.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.ReceivedGift.toDomain(): ReceivedGift = ReceivedGift(
    receivedGiftId = this.receivedGiftId,
    senderId = this.senderId?.toDomain(),
    text = this.text.toDomain(),
    isPrivate = this.isPrivate,
    isSaved = this.isSaved,
    isPinned = this.isPinned,
    canBeUpgraded = this.canBeUpgraded,
    canBeTransferred = this.canBeTransferred,
    wasRefunded = this.wasRefunded,
    date = this.date,
    gift = this.gift.toDomain(),
    collectionIds = this.collectionIds,
    sellStarCount = this.sellStarCount,
    prepaidUpgradeStarCount = this.prepaidUpgradeStarCount,
    isUpgradeSeparate = this.isUpgradeSeparate,
    transferStarCount = this.transferStarCount,
    nextTransferDate = this.nextTransferDate,
    nextResaleDate = this.nextResaleDate,
    exportDate = this.exportDate,
    prepaidUpgradeHash = this.prepaidUpgradeHash
)

fun TdApi.ReceivedGifts.toDomain(): ReceivedGifts = ReceivedGifts(
    totalCount = this.totalCount,
    gifts = this.gifts.map { it.toDomain() },
    areNotificationsEnabled = this.areNotificationsEnabled,
    nextOffset = this.nextOffset
)

