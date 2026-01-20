package com.xxcactussell.domain

data class NetworkStatistics(
    val sinceDate: Int,
    val entries: List<NetworkStatisticsEntry>
) : Object
