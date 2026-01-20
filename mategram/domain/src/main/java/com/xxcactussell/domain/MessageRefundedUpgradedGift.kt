package com.xxcactussell.domain

data class MessageRefundedUpgradedGift(
    val gift: Gift,
    val senderId: MessageSender,
    val receiverId: MessageSender,
    val origin: UpgradedGiftOrigin
) : MessageContent
