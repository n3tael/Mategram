package com.xxcactussell.domain

data class ChatRevenueAmount(
    val cryptocurrency: String,
    val totalAmount: Long,
    val balanceAmount: Long,
    val availableAmount: Long,
    val withdrawalEnabled: Boolean
) : Object
