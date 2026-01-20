package com.xxcactussell.domain

data class TonRevenueStatistics(
    val revenueByDayGraph: StatisticalGraph,
    val status: TonRevenueStatus,
    val usdRate: Double
) : Object
