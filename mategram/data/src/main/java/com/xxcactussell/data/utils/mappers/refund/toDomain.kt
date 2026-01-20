package com.xxcactussell.data.utils.mappers.refund

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.RefundStarPayment.toDomain(): RefundStarPayment = RefundStarPayment(
    userId = this.userId,
    telegramPaymentChargeId = this.telegramPaymentChargeId
)

