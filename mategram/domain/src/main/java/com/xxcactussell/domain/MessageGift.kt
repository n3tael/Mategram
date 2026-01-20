package com.xxcactussell.domain

data class MessageGift(
    val gift: Gift,
    val senderId: MessageSender? = null,
    val receiverId: MessageSender,
    val receivedGiftId: String,
    val text: FormattedText,
    val sellStarCount: Long,
    val prepaidUpgradeStarCount: Long,
    val isUpgradeSeparate: Boolean,
    val isPrivate: Boolean,
    val isSaved: Boolean,
    val isPrepaidUpgrade: Boolean,
    val canBeUpgraded: Boolean,
    val wasConverted: Boolean,
    val wasUpgraded: Boolean,
    val wasRefunded: Boolean,
    val upgradedReceivedGiftId: String,
    val prepaidUpgradeHash: String
) : MessageContent
