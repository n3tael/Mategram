package com.xxcactussell.domain

data class SearchGiftsForResale(
    val giftId: Long,
    val order: GiftForResaleOrder,
    val attributes: List<UpgradedGiftAttributeId>,
    val offset: String,
    val limit: Int
) : Function
