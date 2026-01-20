package com.xxcactussell.domain

data class StarTransactionTypeBotInvoicePurchase(
    val userId: Long,
    val productInfo: ProductInfo
) : StarTransactionType
