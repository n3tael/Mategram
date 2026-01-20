package com.xxcactussell.domain

data class DeleteGiftCollection(
    val ownerId: MessageSender,
    val collectionId: Int
) : Function
