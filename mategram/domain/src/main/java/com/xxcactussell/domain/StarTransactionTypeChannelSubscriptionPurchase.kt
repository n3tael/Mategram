package com.xxcactussell.domain

data class StarTransactionTypeChannelSubscriptionPurchase(
    val chatId: Long,
    val subscriptionPeriod: Int
) : StarTransactionType
