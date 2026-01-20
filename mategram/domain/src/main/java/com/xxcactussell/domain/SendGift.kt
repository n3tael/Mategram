package com.xxcactussell.domain

data class SendGift(
    val giftId: Long,
    val ownerId: MessageSender,
    val text: FormattedText,
    val isPrivate: Boolean,
    val payForUpgrade: Boolean
) : Function
