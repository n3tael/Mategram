package com.xxcactussell.domain

data class SetGiftResalePrice(
    val receivedGiftId: String,
    val price: GiftResalePrice
) : Function
