package com.xxcactussell.domain

data class ChatBoostStatus(
    val boostUrl: String,
    val appliedSlotIds: IntArray,
    val level: Int,
    val giftCodeBoostCount: Int,
    val boostCount: Int,
    val currentLevelBoostCount: Int,
    val nextLevelBoostCount: Int,
    val premiumMemberCount: Int,
    val premiumMemberPercentage: Double,
    val prepaidGiveaways: List<PrepaidGiveaway>
) : Object
