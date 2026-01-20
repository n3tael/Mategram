package com.xxcactussell.domain

data class StarSubscriptionTypeBot(
    val isCanceledByBot: Boolean,
    val title: String,
    val photo: Photo,
    val invoiceLink: String
) : StarSubscriptionType
