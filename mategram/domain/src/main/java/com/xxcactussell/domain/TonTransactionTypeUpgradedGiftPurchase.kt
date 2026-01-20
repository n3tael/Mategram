package com.xxcactussell.domain

data class TonTransactionTypeUpgradedGiftPurchase(
    val userId: Long,
    val gift: UpgradedGift
) : TonTransactionType
