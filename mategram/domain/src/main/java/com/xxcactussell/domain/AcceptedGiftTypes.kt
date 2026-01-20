package com.xxcactussell.domain

data class AcceptedGiftTypes(
    val unlimitedGifts: Boolean,
    val limitedGifts: Boolean,
    val upgradedGifts: Boolean,
    val premiumSubscription: Boolean
) : Object
