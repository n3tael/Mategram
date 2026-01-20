package com.xxcactussell.domain

data class SponsoredMessage(
    val messageId: Long,
    val isRecommended: Boolean,
    val canBeReported: Boolean,
    val content: MessageContent,
    val sponsor: AdvertisementSponsor,
    val title: String,
    val buttonText: String,
    val accentColorId: Int,
    val backgroundCustomEmojiId: Long,
    val additionalInfo: String
) : Object
