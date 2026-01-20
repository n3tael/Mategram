package com.xxcactussell.domain

data class StarTransaction(
    val id: String,
    val starAmount: StarAmount,
    val isRefund: Boolean,
    val date: Int,
    val type: StarTransactionType
) : Object
