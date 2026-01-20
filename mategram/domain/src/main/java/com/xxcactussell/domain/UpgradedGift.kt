package com.xxcactussell.domain

data class UpgradedGift(
    val id: Long,
    val regularGiftId: Long,
    val publisherChatId: Long,
    val title: String,
    val name: String,
    val number: Int,
    val totalUpgradedCount: Int,
    val maxUpgradedCount: Int,
    val isPremium: Boolean,
    val isThemeAvailable: Boolean,
    val usedThemeChatId: Long,
    val ownerId: MessageSender? = null,
    val ownerAddress: String,
    val ownerName: String,
    val giftAddress: String,
    val model: UpgradedGiftModel,
    val symbol: UpgradedGiftSymbol,
    val backdrop: UpgradedGiftBackdrop,
    val originalDetails: UpgradedGiftOriginalDetails? = null,
    val resaleParameters: GiftResaleParameters? = null,
    val valueCurrency: String,
    val valueAmount: Long
) : Object
