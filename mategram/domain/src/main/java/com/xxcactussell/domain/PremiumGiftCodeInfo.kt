package com.xxcactussell.domain

data class PremiumGiftCodeInfo(
    val creatorId: MessageSender? = null,
    val creationDate: Int,
    val isFromGiveaway: Boolean,
    val giveawayMessageId: Long,
    val monthCount: Int,
    val userId: Long,
    val useDate: Int
) : Object
