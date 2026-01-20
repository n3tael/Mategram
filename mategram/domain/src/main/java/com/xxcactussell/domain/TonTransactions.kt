package com.xxcactussell.domain

data class TonTransactions(
    val tonAmount: Long,
    val transactions: List<TonTransaction>,
    val nextOffset: String
) : Object
