package com.xxcactussell.domain

data class ChatRevenueStatistics(
    val revenueByHourGraph: StatisticalGraph,
    val revenueGraph: StatisticalGraph,
    val revenueAmount: ChatRevenueAmount,
    val usdRate: Double
) : Object
