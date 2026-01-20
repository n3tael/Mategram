package com.xxcactussell.domain

data class TonRevenueStatus(
    val totalAmount: Long,
    val balanceAmount: Long,
    val availableAmount: Long,
    val withdrawalEnabled: Boolean
) : Object
