package com.xxcactussell.data.utils.mappers.ton

import com.xxcactussell.data.utils.mappers.monetization.toDomain
import com.xxcactussell.data.utils.mappers.statistical.toDomain
import com.xxcactussell.data.utils.mappers.sticker.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.TonRevenueStatistics.toDomain(): TonRevenueStatistics = TonRevenueStatistics(
    revenueByDayGraph = this.revenueByDayGraph.toDomain(),
    status = this.status.toDomain(),
    usdRate = this.usdRate
)

fun TdApi.TonRevenueStatus.toDomain(): TonRevenueStatus = TonRevenueStatus(
    totalAmount = this.totalAmount,
    balanceAmount = this.balanceAmount,
    availableAmount = this.availableAmount,
    withdrawalEnabled = this.withdrawalEnabled
)

fun TdApi.TonTransaction.toDomain(): TonTransaction = TonTransaction(
    id = this.id,
    tonAmount = this.tonAmount,
    isRefund = this.isRefund,
    date = this.date,
    type = this.type.toDomain()
)

fun TdApi.TonTransactionType.toDomain(): TonTransactionType = when(this) {
    is TdApi.TonTransactionTypeFragmentDeposit -> this.toDomain()
    is TdApi.TonTransactionTypeSuggestedPostPayment -> this.toDomain()
    is TdApi.TonTransactionTypeUpgradedGiftPurchase -> this.toDomain()
    is TdApi.TonTransactionTypeUpgradedGiftSale -> this.toDomain()
    is TdApi.TonTransactionTypeUnsupported -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.TonTransactionTypeFragmentDeposit.toDomain(): TonTransactionTypeFragmentDeposit = TonTransactionTypeFragmentDeposit(
    isGift = this.isGift,
    sticker = this.sticker?.toDomain()
)

fun TdApi.TonTransactionTypeSuggestedPostPayment.toDomain(): TonTransactionTypeSuggestedPostPayment = TonTransactionTypeSuggestedPostPayment(
    chatId = this.chatId
)

fun TdApi.TonTransactionTypeUnsupported.toDomain(): TonTransactionTypeUnsupported = TonTransactionTypeUnsupported

fun TdApi.TonTransactionTypeUpgradedGiftPurchase.toDomain(): TonTransactionTypeUpgradedGiftPurchase = TonTransactionTypeUpgradedGiftPurchase(
    userId = this.userId,
    gift = this.gift.toDomain()
)

fun TdApi.TonTransactionTypeUpgradedGiftSale.toDomain(): TonTransactionTypeUpgradedGiftSale = TonTransactionTypeUpgradedGiftSale(
    userId = this.userId,
    gift = this.gift.toDomain(),
    commissionPerMille = this.commissionPerMille,
    commissionToncoinAmount = this.commissionToncoinAmount
)

fun TdApi.TonTransactions.toDomain(): TonTransactions = TonTransactions(
    tonAmount = this.tonAmount,
    transactions = this.transactions.map { it.toDomain() },
    nextOffset = this.nextOffset
)

