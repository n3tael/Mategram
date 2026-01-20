package com.xxcactussell.domain

data class StarSubscription(
    val id: String,
    val chatId: Long,
    val expirationDate: Int,
    val isCanceled: Boolean,
    val isExpiring: Boolean,
    val pricing: StarSubscriptionPricing,
    val type: StarSubscriptionType
) : Object
