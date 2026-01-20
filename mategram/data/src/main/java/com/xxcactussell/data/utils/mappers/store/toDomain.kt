package com.xxcactussell.data.utils.mappers.store

import com.xxcactussell.data.utils.mappers.formatted.toDomain
import com.xxcactussell.data.utils.mappers.giveaway.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.StorePaymentPurpose.toDomain(): StorePaymentPurpose = when(this) {
    is TdApi.StorePaymentPurposePremiumSubscription -> this.toDomain()
    is TdApi.StorePaymentPurposePremiumGift -> this.toDomain()
    is TdApi.StorePaymentPurposePremiumGiftCodes -> this.toDomain()
    is TdApi.StorePaymentPurposePremiumGiveaway -> this.toDomain()
    is TdApi.StorePaymentPurposeStarGiveaway -> this.toDomain()
    is TdApi.StorePaymentPurposeStars -> this.toDomain()
    is TdApi.StorePaymentPurposeGiftedStars -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.StorePaymentPurposeGiftedStars.toDomain(): StorePaymentPurposeGiftedStars = StorePaymentPurposeGiftedStars(
    userId = this.userId,
    currency = this.currency,
    amount = this.amount,
    starCount = this.starCount
)

fun TdApi.StorePaymentPurposePremiumGift.toDomain(): StorePaymentPurposePremiumGift = StorePaymentPurposePremiumGift(
    currency = this.currency,
    amount = this.amount,
    userId = this.userId,
    text = this.text.toDomain()
)

fun TdApi.StorePaymentPurposePremiumGiftCodes.toDomain(): StorePaymentPurposePremiumGiftCodes = StorePaymentPurposePremiumGiftCodes(
    boostedChatId = this.boostedChatId,
    currency = this.currency,
    amount = this.amount,
    userIds = this.userIds,
    text = this.text.toDomain()
)

fun TdApi.StorePaymentPurposePremiumGiveaway.toDomain(): StorePaymentPurposePremiumGiveaway = StorePaymentPurposePremiumGiveaway(
    parameters = this.parameters.toDomain(),
    currency = this.currency,
    amount = this.amount
)

fun TdApi.StorePaymentPurposePremiumSubscription.toDomain(): StorePaymentPurposePremiumSubscription = StorePaymentPurposePremiumSubscription(
    isRestore = this.isRestore,
    isUpgrade = this.isUpgrade
)

fun TdApi.StorePaymentPurposeStarGiveaway.toDomain(): StorePaymentPurposeStarGiveaway = StorePaymentPurposeStarGiveaway(
    parameters = this.parameters.toDomain(),
    currency = this.currency,
    amount = this.amount,
    winnerCount = this.winnerCount,
    starCount = this.starCount
)

fun TdApi.StorePaymentPurposeStars.toDomain(): StorePaymentPurposeStars = StorePaymentPurposeStars(
    currency = this.currency,
    amount = this.amount,
    starCount = this.starCount,
    chatId = this.chatId
)

fun TdApi.StoreTransaction.toDomain(): StoreTransaction = when(this) {
    is TdApi.StoreTransactionAppStore -> this.toDomain()
    is TdApi.StoreTransactionGooglePlay -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.StoreTransactionAppStore.toDomain(): StoreTransactionAppStore = StoreTransactionAppStore(
    receipt = this.receipt
)

fun TdApi.StoreTransactionGooglePlay.toDomain(): StoreTransactionGooglePlay = StoreTransactionGooglePlay(
    packageName = this.packageName,
    storeProductId = this.storeProductId,
    purchaseToken = this.purchaseToken
)

