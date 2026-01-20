package com.xxcactussell.domain

data class StarTransactionTypeGiftPurchase(
    val ownerId: MessageSender,
    val gift: Gift
) : StarTransactionType
