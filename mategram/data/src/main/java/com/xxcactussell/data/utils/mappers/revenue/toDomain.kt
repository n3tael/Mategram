package com.xxcactussell.data.utils.mappers.revenue

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.RevenueWithdrawalState.toDomain(): RevenueWithdrawalState = when(this) {
    is TdApi.RevenueWithdrawalStatePending -> this.toDomain()
    is TdApi.RevenueWithdrawalStateSucceeded -> this.toDomain()
    is TdApi.RevenueWithdrawalStateFailed -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.RevenueWithdrawalStateFailed.toDomain(): RevenueWithdrawalStateFailed = RevenueWithdrawalStateFailed

fun TdApi.RevenueWithdrawalStatePending.toDomain(): RevenueWithdrawalStatePending = RevenueWithdrawalStatePending

fun TdApi.RevenueWithdrawalStateSucceeded.toDomain(): RevenueWithdrawalStateSucceeded = RevenueWithdrawalStateSucceeded(
    date = this.date,
    url = this.url
)

