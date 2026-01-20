package com.xxcactussell.domain

data class StarTransactionTypePremiumPurchase(
    val userId: Long,
    val monthCount: Int,
    val sticker: Sticker? = null
) : StarTransactionType
