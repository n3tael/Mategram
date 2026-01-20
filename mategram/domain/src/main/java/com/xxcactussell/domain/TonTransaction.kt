package com.xxcactussell.domain

data class TonTransaction(
    val id: String,
    val tonAmount: Long,
    val isRefund: Boolean,
    val date: Int,
    val type: TonTransactionType
) : Object
