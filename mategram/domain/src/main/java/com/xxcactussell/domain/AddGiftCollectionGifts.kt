package com.xxcactussell.domain

data class AddGiftCollectionGifts(
    val ownerId: MessageSender,
    val collectionId: Int,
    val receivedGiftIds: List<String>
) : Function
