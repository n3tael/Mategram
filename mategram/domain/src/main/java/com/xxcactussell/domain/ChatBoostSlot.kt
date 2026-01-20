package com.xxcactussell.domain

data class ChatBoostSlot(
    val slotId: Int,
    val currentlyBoostedChatId: Long,
    val startDate: Int,
    val expirationDate: Int,
    val cooldownUntilDate: Int
) : Object
