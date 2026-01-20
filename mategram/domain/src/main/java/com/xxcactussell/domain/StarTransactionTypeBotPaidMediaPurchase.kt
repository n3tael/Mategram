package com.xxcactussell.domain

data class StarTransactionTypeBotPaidMediaPurchase(
    val userId: Long,
    val media: List<PaidMedia>
) : StarTransactionType
