package com.xxcactussell.domain

data class StarTransactionTypeChannelPaidMediaPurchase(
    val chatId: Long,
    val messageId: Long,
    val media: List<PaidMedia>
) : StarTransactionType
