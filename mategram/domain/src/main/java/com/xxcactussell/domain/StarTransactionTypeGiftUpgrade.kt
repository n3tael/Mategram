package com.xxcactussell.domain

data class StarTransactionTypeGiftUpgrade(
    val userId: Long,
    val gift: UpgradedGift
) : StarTransactionType
