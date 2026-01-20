package com.xxcactussell.data.utils.mappers.star

import com.xxcactussell.data.utils.mappers.photo.toData
import com.xxcactussell.data.utils.mappers.statistical.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun StarAmount.toData(): TdApi.StarAmount = TdApi.StarAmount(
    this.starCount,
    this.nanostarCount
)

fun StarCount.toData(): TdApi.StarCount = TdApi.StarCount(
    this.starCount
)

fun StarGiveawayPaymentOption.toData(): TdApi.StarGiveawayPaymentOption = TdApi.StarGiveawayPaymentOption(
    this.currency,
    this.amount,
    this.starCount,
    this.storeProductId,
    this.yearlyBoostCount,
    this.winnerOptions.map { it.toData() }.toTypedArray(),
    this.isDefault,
    this.isAdditional
)

fun StarGiveawayPaymentOptions.toData(): TdApi.StarGiveawayPaymentOptions = TdApi.StarGiveawayPaymentOptions(
    this.options.map { it.toData() }.toTypedArray()
)

fun StarGiveawayWinnerOption.toData(): TdApi.StarGiveawayWinnerOption = TdApi.StarGiveawayWinnerOption(
    this.winnerCount,
    this.wonStarCount,
    this.isDefault
)

fun StarPaymentOption.toData(): TdApi.StarPaymentOption = TdApi.StarPaymentOption(
    this.currency,
    this.amount,
    this.starCount,
    this.storeProductId,
    this.isAdditional
)

fun StarPaymentOptions.toData(): TdApi.StarPaymentOptions = TdApi.StarPaymentOptions(
    this.options.map { it.toData() }.toTypedArray()
)

fun StarRevenueStatistics.toData(): TdApi.StarRevenueStatistics = TdApi.StarRevenueStatistics(
    this.revenueByDayGraph.toData(),
    this.status.toData(),
    this.usdRate
)

fun StarRevenueStatus.toData(): TdApi.StarRevenueStatus = TdApi.StarRevenueStatus(
    this.totalAmount.toData(),
    this.currentAmount.toData(),
    this.availableAmount.toData(),
    this.withdrawalEnabled,
    this.nextWithdrawalIn
)

fun StarSubscription.toData(): TdApi.StarSubscription = TdApi.StarSubscription(
    this.id,
    this.chatId,
    this.expirationDate,
    this.isCanceled,
    this.isExpiring,
    this.pricing.toData(),
    this.type.toData()
)

fun StarSubscriptionPricing.toData(): TdApi.StarSubscriptionPricing = TdApi.StarSubscriptionPricing(
    this.period,
    this.starCount
)

fun StarSubscriptionType.toData(): TdApi.StarSubscriptionType = when(this) {
    is StarSubscriptionTypeChannel -> this.toData()
    is StarSubscriptionTypeBot -> this.toData()
}

fun StarSubscriptionTypeBot.toData(): TdApi.StarSubscriptionTypeBot = TdApi.StarSubscriptionTypeBot(
    this.isCanceledByBot,
    this.title,
    this.photo.toData(),
    this.invoiceLink
)

fun StarSubscriptionTypeChannel.toData(): TdApi.StarSubscriptionTypeChannel = TdApi.StarSubscriptionTypeChannel(
    this.canReuse,
    this.inviteLink
)

fun StarSubscriptions.toData(): TdApi.StarSubscriptions = TdApi.StarSubscriptions(
    this.starAmount.toData(),
    this.subscriptions.map { it.toData() }.toTypedArray(),
    this.requiredStarCount,
    this.nextOffset
)

