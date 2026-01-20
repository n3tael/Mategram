package com.xxcactussell.data.utils.mappers.refund

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun RefundStarPayment.toData(): TdApi.RefundStarPayment = TdApi.RefundStarPayment(
    this.userId,
    this.telegramPaymentChargeId
)

