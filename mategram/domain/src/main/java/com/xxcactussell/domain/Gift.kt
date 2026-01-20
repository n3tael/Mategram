package com.xxcactussell.domain

data class Gift(
    val id: Long,
    val publisherChatId: Long,
    val sticker: Sticker,
    val starCount: Long,
    val defaultSellStarCount: Long,
    val upgradeStarCount: Long,
    val isForBirthday: Boolean,
    val isPremium: Boolean,
    val nextSendDate: Int,
    val userLimits: GiftPurchaseLimits? = null,
    val overallLimits: GiftPurchaseLimits? = null,
    val firstSendDate: Int,
    val lastSendDate: Int
) : Object
