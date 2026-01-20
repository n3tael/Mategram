package com.xxcactussell.domain

data class TonTransactionTypeUpgradedGiftSale(
    val userId: Long,
    val gift: UpgradedGift,
    val commissionPerMille: Int,
    val commissionToncoinAmount: Long
) : TonTransactionType
