package com.xxcactussell.domain

data class CreateChatSubscriptionInviteLink(
    val chatId: Long,
    val name: String,
    val subscriptionPricing: StarSubscriptionPricing
) : Function
