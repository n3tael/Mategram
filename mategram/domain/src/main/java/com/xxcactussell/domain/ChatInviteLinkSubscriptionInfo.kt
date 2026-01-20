package com.xxcactussell.domain

data class ChatInviteLinkSubscriptionInfo(
    val pricing: StarSubscriptionPricing,
    val canReuse: Boolean,
    val formId: Long
) : Object
