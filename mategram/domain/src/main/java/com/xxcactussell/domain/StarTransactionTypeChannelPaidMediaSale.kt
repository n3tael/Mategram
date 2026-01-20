package com.xxcactussell.domain

data class StarTransactionTypeChannelPaidMediaSale(
    val userId: Long,
    val messageId: Long,
    val media: List<PaidMedia>
) : StarTransactionType
