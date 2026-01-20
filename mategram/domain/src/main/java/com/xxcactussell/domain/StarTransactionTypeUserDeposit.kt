package com.xxcactussell.domain

data class StarTransactionTypeUserDeposit(
    val userId: Long,
    val sticker: Sticker? = null
) : StarTransactionType
