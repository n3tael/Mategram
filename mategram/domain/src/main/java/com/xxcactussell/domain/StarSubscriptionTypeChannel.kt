package com.xxcactussell.domain

data class StarSubscriptionTypeChannel(
    val canReuse: Boolean,
    val inviteLink: String
) : StarSubscriptionType
