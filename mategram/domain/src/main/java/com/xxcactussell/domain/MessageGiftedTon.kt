package com.xxcactussell.domain

data class MessageGiftedTon(
    val gifterUserId: Long,
    val receiverUserId: Long,
    val tonAmount: Long,
    val transactionId: String,
    val sticker: Sticker? = null
) : MessageContent
