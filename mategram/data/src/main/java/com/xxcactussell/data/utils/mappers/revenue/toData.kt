package com.xxcactussell.data.utils.mappers.revenue

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun RevenueWithdrawalState.toData(): TdApi.RevenueWithdrawalState = when(this) {
    is RevenueWithdrawalStatePending -> this.toData()
    is RevenueWithdrawalStateSucceeded -> this.toData()
    is RevenueWithdrawalStateFailed -> this.toData()
}

fun RevenueWithdrawalStateFailed.toData(): TdApi.RevenueWithdrawalStateFailed = TdApi.RevenueWithdrawalStateFailed(
)

fun RevenueWithdrawalStatePending.toData(): TdApi.RevenueWithdrawalStatePending = TdApi.RevenueWithdrawalStatePending(
)

fun RevenueWithdrawalStateSucceeded.toData(): TdApi.RevenueWithdrawalStateSucceeded = TdApi.RevenueWithdrawalStateSucceeded(
    this.date,
    this.url
)

