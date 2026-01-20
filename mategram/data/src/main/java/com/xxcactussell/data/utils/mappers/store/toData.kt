package com.xxcactussell.data.utils.mappers.store

import com.xxcactussell.data.utils.mappers.formatted.toData
import com.xxcactussell.data.utils.mappers.giveaway.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun StorePaymentPurpose.toData(): TdApi.StorePaymentPurpose = when(this) {
    is StorePaymentPurposePremiumSubscription -> this.toData()
    is StorePaymentPurposePremiumGift -> this.toData()
    is StorePaymentPurposePremiumGiftCodes -> this.toData()
    is StorePaymentPurposePremiumGiveaway -> this.toData()
    is StorePaymentPurposeStarGiveaway -> this.toData()
    is StorePaymentPurposeStars -> this.toData()
    is StorePaymentPurposeGiftedStars -> this.toData()
}

fun StorePaymentPurposeGiftedStars.toData(): TdApi.StorePaymentPurposeGiftedStars = TdApi.StorePaymentPurposeGiftedStars(
    this.userId,
    this.currency,
    this.amount,
    this.starCount
)

fun StorePaymentPurposePremiumGift.toData(): TdApi.StorePaymentPurposePremiumGift = TdApi.StorePaymentPurposePremiumGift(
    this.currency,
    this.amount,
    this.userId,
    this.text.toData()
)

fun StorePaymentPurposePremiumGiftCodes.toData(): TdApi.StorePaymentPurposePremiumGiftCodes = TdApi.StorePaymentPurposePremiumGiftCodes(
    this.boostedChatId,
    this.currency,
    this.amount,
    this.userIds,
    this.text.toData()
)

fun StorePaymentPurposePremiumGiveaway.toData(): TdApi.StorePaymentPurposePremiumGiveaway = TdApi.StorePaymentPurposePremiumGiveaway(
    this.parameters.toData(),
    this.currency,
    this.amount
)

fun StorePaymentPurposePremiumSubscription.toData(): TdApi.StorePaymentPurposePremiumSubscription = TdApi.StorePaymentPurposePremiumSubscription(
    this.isRestore,
    this.isUpgrade
)

fun StorePaymentPurposeStarGiveaway.toData(): TdApi.StorePaymentPurposeStarGiveaway = TdApi.StorePaymentPurposeStarGiveaway(
    this.parameters.toData(),
    this.currency,
    this.amount,
    this.winnerCount,
    this.starCount
)

fun StorePaymentPurposeStars.toData(): TdApi.StorePaymentPurposeStars = TdApi.StorePaymentPurposeStars(
    this.currency,
    this.amount,
    this.starCount,
    this.chatId
)

fun StoreTransaction.toData(): TdApi.StoreTransaction = when(this) {
    is StoreTransactionAppStore -> this.toData()
    is StoreTransactionGooglePlay -> this.toData()
}

fun StoreTransactionAppStore.toData(): TdApi.StoreTransactionAppStore = TdApi.StoreTransactionAppStore(
    this.receipt
)

fun StoreTransactionGooglePlay.toData(): TdApi.StoreTransactionGooglePlay = TdApi.StoreTransactionGooglePlay(
    this.packageName,
    this.storeProductId,
    this.purchaseToken
)

