package com.xxcactussell.domain

data class ChatRevenueTransaction(
    val cryptocurrency: String,
    val cryptocurrencyAmount: Long,
    val type: ChatRevenueTransactionType
) : Object
