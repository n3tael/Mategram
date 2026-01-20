package com.xxcactussell.domain

data class StarRevenueStatus(
    val totalAmount: StarAmount,
    val currentAmount: StarAmount,
    val availableAmount: StarAmount,
    val withdrawalEnabled: Boolean,
    val nextWithdrawalIn: Int
) : Object
