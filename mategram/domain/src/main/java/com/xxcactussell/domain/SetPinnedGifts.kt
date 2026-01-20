package com.xxcactussell.domain

data class SetPinnedGifts(
    val ownerId: MessageSender,
    val receivedGiftIds: List<String>
) : Function
