package com.xxcactussell.domain

data class StarTransactionTypeUpgradedGiftSale(
    val userId: Long,
    val gift: UpgradedGift,
    val commissionPerMille: Int,
    val commissionStarAmount: StarAmount
) : StarTransactionType
