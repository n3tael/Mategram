package com.xxcactussell.domain

data class ReorderGiftCollections(
    val ownerId: MessageSender,
    val collectionIds: IntArray
) : Function
