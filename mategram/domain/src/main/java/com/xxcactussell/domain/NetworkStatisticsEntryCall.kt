package com.xxcactussell.domain

data class NetworkStatisticsEntryCall(
    val networkType: NetworkType,
    val sentBytes: Long,
    val receivedBytes: Long,
    val duration: Double
) : NetworkStatisticsEntry
