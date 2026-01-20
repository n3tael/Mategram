package com.xxcactussell.domain

data class ChatRevenueTransactions(
    val tonAmount: Long,
    val transactions: List<ChatRevenueTransaction>,
    val nextOffset: String
) : Object
