package com.xxcactussell.domain

data class ReceivedGift(
    val receivedGiftId: String,
    val senderId: MessageSender? = null,
    val text: FormattedText,
    val isPrivate: Boolean,
    val isSaved: Boolean,
    val isPinned: Boolean,
    val canBeUpgraded: Boolean,
    val canBeTransferred: Boolean,
    val wasRefunded: Boolean,
    val date: Int,
    val gift: SentGift,
    val collectionIds: IntArray,
    val sellStarCount: Long,
    val prepaidUpgradeStarCount: Long,
    val isUpgradeSeparate: Boolean,
    val transferStarCount: Long,
    val nextTransferDate: Int,
    val nextResaleDate: Int,
    val exportDate: Int,
    val prepaidUpgradeHash: String
) : Object
