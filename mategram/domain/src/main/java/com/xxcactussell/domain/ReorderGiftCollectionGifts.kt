package com.xxcactussell.domain

data class ReorderGiftCollectionGifts(
    val ownerId: MessageSender,
    val collectionId: Int,
    val receivedGiftIds: List<String>
) : Function
