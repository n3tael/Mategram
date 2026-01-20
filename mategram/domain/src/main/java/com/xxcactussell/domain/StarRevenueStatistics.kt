package com.xxcactussell.domain

data class StarRevenueStatistics(
    val revenueByDayGraph: StatisticalGraph,
    val status: StarRevenueStatus,
    val usdRate: Double
) : Object
