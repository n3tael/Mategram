package com.xxcactussell.domain

data class UpgradeGift(
    val businessConnectionId: String,
    val receivedGiftId: String,
    val keepOriginalDetails: Boolean,
    val starCount: Long
) : Function
