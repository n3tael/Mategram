package com.xxcactussell.domain

data class SendResoldGift(
    val giftName: String,
    val ownerId: MessageSender,
    val price: GiftResalePrice
) : Function
