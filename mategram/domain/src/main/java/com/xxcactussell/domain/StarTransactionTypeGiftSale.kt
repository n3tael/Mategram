package com.xxcactussell.domain

data class StarTransactionTypeGiftSale(
    val userId: Long,
    val gift: Gift
) : StarTransactionType
