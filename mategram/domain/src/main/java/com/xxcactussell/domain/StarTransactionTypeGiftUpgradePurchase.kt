package com.xxcactussell.domain

data class StarTransactionTypeGiftUpgradePurchase(
    val ownerId: MessageSender,
    val gift: Gift
) : StarTransactionType
