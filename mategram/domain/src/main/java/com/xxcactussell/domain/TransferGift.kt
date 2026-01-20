package com.xxcactussell.domain

data class TransferGift(
    val businessConnectionId: String,
    val receivedGiftId: String,
    val newOwnerId: MessageSender,
    val starCount: Long
) : Function
