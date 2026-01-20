package com.xxcactussell.domain

data class StarTransactionTypeBotSubscriptionPurchase(
    val userId: Long,
    val subscriptionPeriod: Int,
    val productInfo: ProductInfo
) : StarTransactionType
