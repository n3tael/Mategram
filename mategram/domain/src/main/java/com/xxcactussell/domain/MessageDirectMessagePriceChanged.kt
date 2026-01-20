package com.xxcactussell.domain

data class MessageDirectMessagePriceChanged(
    val isEnabled: Boolean,
    val paidMessageStarCount: Long
) : MessageContent
