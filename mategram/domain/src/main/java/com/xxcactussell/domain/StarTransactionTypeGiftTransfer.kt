package com.xxcactussell.domain

data class StarTransactionTypeGiftTransfer(
    val ownerId: MessageSender,
    val gift: UpgradedGift
) : StarTransactionType
