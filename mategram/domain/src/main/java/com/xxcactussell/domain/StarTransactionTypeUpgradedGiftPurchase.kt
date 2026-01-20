package com.xxcactussell.domain

data class StarTransactionTypeUpgradedGiftPurchase(
    val userId: Long,
    val gift: UpgradedGift
) : StarTransactionType
