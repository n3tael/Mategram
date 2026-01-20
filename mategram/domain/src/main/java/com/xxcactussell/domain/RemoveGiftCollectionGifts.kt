package com.xxcactussell.domain

data class RemoveGiftCollectionGifts(
    val ownerId: MessageSender,
    val collectionId: Int,
    val receivedGiftIds: List<String>
) : Function
