package com.xxcactussell.data.utils.mappers.ton

import com.xxcactussell.data.utils.mappers.monetization.toData
import com.xxcactussell.data.utils.mappers.statistical.toData
import com.xxcactussell.data.utils.mappers.sticker.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun TonRevenueStatistics.toData(): TdApi.TonRevenueStatistics = TdApi.TonRevenueStatistics(
    this.revenueByDayGraph.toData(),
    this.status.toData(),
    this.usdRate
)

fun TonRevenueStatus.toData(): TdApi.TonRevenueStatus = TdApi.TonRevenueStatus(
    this.totalAmount,
    this.balanceAmount,
    this.availableAmount,
    this.withdrawalEnabled
)

fun TonTransaction.toData(): TdApi.TonTransaction = TdApi.TonTransaction(
    this.id,
    this.tonAmount,
    this.isRefund,
    this.date,
    this.type.toData()
)

fun TonTransactionType.toData(): TdApi.TonTransactionType = when(this) {
    is TonTransactionTypeFragmentDeposit -> this.toData()
    is TonTransactionTypeSuggestedPostPayment -> this.toData()
    is TonTransactionTypeUpgradedGiftPurchase -> this.toData()
    is TonTransactionTypeUpgradedGiftSale -> this.toData()
    is TonTransactionTypeUnsupported -> this.toData()
}

fun TonTransactionTypeFragmentDeposit.toData(): TdApi.TonTransactionTypeFragmentDeposit = TdApi.TonTransactionTypeFragmentDeposit(
    this.isGift,
    this.sticker?.toData()
)

fun TonTransactionTypeSuggestedPostPayment.toData(): TdApi.TonTransactionTypeSuggestedPostPayment = TdApi.TonTransactionTypeSuggestedPostPayment(
    this.chatId
)

fun TonTransactionTypeUnsupported.toData(): TdApi.TonTransactionTypeUnsupported = TdApi.TonTransactionTypeUnsupported(
)

fun TonTransactionTypeUpgradedGiftPurchase.toData(): TdApi.TonTransactionTypeUpgradedGiftPurchase = TdApi.TonTransactionTypeUpgradedGiftPurchase(
    this.userId,
    this.gift.toData()
)

fun TonTransactionTypeUpgradedGiftSale.toData(): TdApi.TonTransactionTypeUpgradedGiftSale = TdApi.TonTransactionTypeUpgradedGiftSale(
    this.userId,
    this.gift.toData(),
    this.commissionPerMille,
    this.commissionToncoinAmount
)

fun TonTransactions.toData(): TdApi.TonTransactions = TdApi.TonTransactions(
    this.tonAmount,
    this.transactions.map { it.toData() }.toTypedArray(),
    this.nextOffset
)

