package com.xxcactussell.domain

data class StarTransactionTypeBotInvoiceSale(
    val userId: Long,
    val productInfo: ProductInfo,
    val invoicePayload: ByteArray,
    val affiliate: AffiliateInfo? = null
) : StarTransactionType
