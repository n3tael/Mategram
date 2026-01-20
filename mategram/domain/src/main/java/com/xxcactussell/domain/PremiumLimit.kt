package com.xxcactussell.domain

data class PremiumLimit(
    val type: PremiumLimitType,
    val defaultValue: Int,
    val premiumValue: Int
) : Object
