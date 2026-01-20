package com.xxcactussell.domain

data class StatisticalValue(
    val value: Double,
    val previousValue: Double,
    val growthRatePercentage: Double
) : Object
