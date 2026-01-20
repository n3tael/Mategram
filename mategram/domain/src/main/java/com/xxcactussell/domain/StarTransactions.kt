package com.xxcactussell.domain

data class StarTransactions(
    val starAmount: StarAmount,
    val transactions: List<StarTransaction>,
    val nextOffset: String
) : Object
