package com.xxcactussell.domain

data class BuyGiftUpgrade(
    val ownerId: MessageSender,
    val prepaidUpgradeHash: String,
    val starCount: Long
) : Function
