package com.xxcactussell.data.utils.mappers.star

import com.xxcactussell.data.utils.mappers.photo.toDomain
import com.xxcactussell.data.utils.mappers.statistical.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.StarAmount.toDomain(): StarAmount = StarAmount(
    starCount = this.starCount,
    nanostarCount = this.nanostarCount
)

fun TdApi.StarCount.toDomain(): StarCount = StarCount(
    starCount = this.starCount
)

fun TdApi.StarGiveawayPaymentOption.toDomain(): StarGiveawayPaymentOption = StarGiveawayPaymentOption(
    currency = this.currency,
    amount = this.amount,
    starCount = this.starCount,
    storeProductId = this.storeProductId,
    yearlyBoostCount = this.yearlyBoostCount,
    winnerOptions = this.winnerOptions.map { it.toDomain() },
    isDefault = this.isDefault,
    isAdditional = this.isAdditional
)

fun TdApi.StarGiveawayPaymentOptions.toDomain(): StarGiveawayPaymentOptions = StarGiveawayPaymentOptions(
    options = this.options.map { it.toDomain() }
)

fun TdApi.StarGiveawayWinnerOption.toDomain(): StarGiveawayWinnerOption = StarGiveawayWinnerOption(
    winnerCount = this.winnerCount,
    wonStarCount = this.wonStarCount,
    isDefault = this.isDefault
)

fun TdApi.StarPaymentOption.toDomain(): StarPaymentOption = StarPaymentOption(
    currency = this.currency,
    amount = this.amount,
    starCount = this.starCount,
    storeProductId = this.storeProductId,
    isAdditional = this.isAdditional
)

fun TdApi.StarPaymentOptions.toDomain(): StarPaymentOptions = StarPaymentOptions(
    options = this.options.map { it.toDomain() }
)

fun TdApi.StarRevenueStatistics.toDomain(): StarRevenueStatistics = StarRevenueStatistics(
    revenueByDayGraph = this.revenueByDayGraph.toDomain(),
    status = this.status.toDomain(),
    usdRate = this.usdRate
)

fun TdApi.StarRevenueStatus.toDomain(): StarRevenueStatus = StarRevenueStatus(
    totalAmount = this.totalAmount.toDomain(),
    currentAmount = this.currentAmount.toDomain(),
    availableAmount = this.availableAmount.toDomain(),
    withdrawalEnabled = this.withdrawalEnabled,
    nextWithdrawalIn = this.nextWithdrawalIn
)

fun TdApi.StarSubscription.toDomain(): StarSubscription = StarSubscription(
    id = this.id,
    chatId = this.chatId,
    expirationDate = this.expirationDate,
    isCanceled = this.isCanceled,
    isExpiring = this.isExpiring,
    pricing = this.pricing.toDomain(),
    type = this.type.toDomain()
)

fun TdApi.StarSubscriptionPricing.toDomain(): StarSubscriptionPricing = StarSubscriptionPricing(
    period = this.period,
    starCount = this.starCount
)

fun TdApi.StarSubscriptionType.toDomain(): StarSubscriptionType = when(this) {
    is TdApi.StarSubscriptionTypeChannel -> this.toDomain()
    is TdApi.StarSubscriptionTypeBot -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.StarSubscriptionTypeBot.toDomain(): StarSubscriptionTypeBot = StarSubscriptionTypeBot(
    isCanceledByBot = this.isCanceledByBot,
    title = this.title,
    photo = this.photo.toDomain(),
    invoiceLink = this.invoiceLink
)

fun TdApi.StarSubscriptionTypeChannel.toDomain(): StarSubscriptionTypeChannel = StarSubscriptionTypeChannel(
    canReuse = this.canReuse,
    inviteLink = this.inviteLink
)

fun TdApi.StarSubscriptions.toDomain(): StarSubscriptions = StarSubscriptions(
    starAmount = this.starAmount.toDomain(),
    subscriptions = this.subscriptions.map { it.toDomain() },
    requiredStarCount = this.requiredStarCount,
    nextOffset = this.nextOffset
)

