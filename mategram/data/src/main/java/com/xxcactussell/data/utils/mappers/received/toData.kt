package com.xxcactussell.data.utils.mappers.received

import com.xxcactussell.data.utils.mappers.formatted.toData
import com.xxcactussell.data.utils.mappers.message.toData
import com.xxcactussell.data.utils.mappers.sent.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun ReceivedGift.toData(): TdApi.ReceivedGift = TdApi.ReceivedGift(
    this.receivedGiftId,
    this.senderId?.toData(),
    this.text.toData(),
    this.isPrivate,
    this.isSaved,
    this.isPinned,
    this.canBeUpgraded,
    this.canBeTransferred,
    this.wasRefunded,
    this.date,
    this.gift.toData(),
    this.collectionIds,
    this.sellStarCount,
    this.prepaidUpgradeStarCount,
    this.isUpgradeSeparate,
    this.transferStarCount,
    this.nextTransferDate,
    this.nextResaleDate,
    this.exportDate,
    this.prepaidUpgradeHash
)

fun ReceivedGifts.toData(): TdApi.ReceivedGifts = TdApi.ReceivedGifts(
    this.totalCount,
    this.gifts.map { it.toData() }.toTypedArray(),
    this.areNotificationsEnabled,
    this.nextOffset
)

