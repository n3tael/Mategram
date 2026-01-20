package com.xxcactussell.domain

data class SetGiftCollectionName(
    val ownerId: MessageSender,
    val collectionId: Int,
    val name: String
) : Function
