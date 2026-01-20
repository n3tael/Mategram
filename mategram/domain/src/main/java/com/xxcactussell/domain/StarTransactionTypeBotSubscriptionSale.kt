package com.xxcactussell.domain

data class StarTransactionTypeBotSubscriptionSale(
    val userId: Long,
    val subscriptionPeriod: Int,
    val productInfo: ProductInfo,
    val invoicePayload: ByteArray,
    val affiliate: AffiliateInfo? = null
) : StarTransactionType
