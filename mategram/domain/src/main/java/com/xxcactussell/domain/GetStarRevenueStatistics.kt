package com.xxcactussell.domain

data class GetStarRevenueStatistics(
    val ownerId: MessageSender,
    val isDark: Boolean
) : Function
