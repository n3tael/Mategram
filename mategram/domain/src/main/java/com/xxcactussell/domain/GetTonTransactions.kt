package com.xxcactussell.domain

data class GetTonTransactions(
    val direction: TransactionDirection,
    val offset: String,
    val limit: Int
) : Function
