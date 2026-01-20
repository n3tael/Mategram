package com.xxcactussell.domain

data class StarSubscriptions(
    val starAmount: StarAmount,
    val subscriptions: List<StarSubscription>,
    val requiredStarCount: Long,
    val nextOffset: String
) : Object
