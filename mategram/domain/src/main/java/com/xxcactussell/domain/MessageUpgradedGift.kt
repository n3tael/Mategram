package com.xxcactussell.domain

data class MessageUpgradedGift(
    val gift: UpgradedGift,
    val senderId: MessageSender? = null,
    val receiverId: MessageSender,
    val origin: UpgradedGiftOrigin,
    val receivedGiftId: String,
    val isSaved: Boolean,
    val canBeTransferred: Boolean,
    val wasTransferred: Boolean,
    val transferStarCount: Long,
    val nextTransferDate: Int,
    val nextResaleDate: Int,
    val exportDate: Int
) : MessageContent
