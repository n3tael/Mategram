package com.xxcactussell.domain

data class CreateGiftCollection(
    val ownerId: MessageSender,
    val name: String,
    val receivedGiftIds: List<String>
) : Function
