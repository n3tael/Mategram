package com.xxcactussell.domain

data class UpgradeGiftResult(
    val gift: UpgradedGift,
    val receivedGiftId: String,
    val isSaved: Boolean,
    val canBeTransferred: Boolean,
    val transferStarCount: Long,
    val nextTransferDate: Int,
    val nextResaleDate: Int,
    val exportDate: Int
) : Object
