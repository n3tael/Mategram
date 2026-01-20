package com.xxcactussell.domain

data class StarTransactionTypeBotPaidMediaSale(
    val userId: Long,
    val media: List<PaidMedia>,
    val payload: String,
    val affiliate: AffiliateInfo? = null
) : StarTransactionType
