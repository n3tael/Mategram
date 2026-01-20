package com.xxcactussell.domain

data class PushMessageContentUpgradedGift(
    val isUpgrade: Boolean,
    val isPrepaidUpgrade: Boolean
) : PushMessageContent
