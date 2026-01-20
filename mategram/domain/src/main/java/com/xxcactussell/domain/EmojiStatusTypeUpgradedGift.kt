package com.xxcactussell.domain

data class EmojiStatusTypeUpgradedGift(
    val upgradedGiftId: Long,
    val giftTitle: String,
    val giftName: String,
    val modelCustomEmojiId: Long,
    val symbolCustomEmojiId: Long,
    val backdropColors: UpgradedGiftBackdropColors
) : EmojiStatusType
