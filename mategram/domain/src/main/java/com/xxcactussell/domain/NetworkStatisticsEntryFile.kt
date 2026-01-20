package com.xxcactussell.domain

data class NetworkStatisticsEntryFile(
    val fileType: FileType,
    val networkType: NetworkType,
    val sentBytes: Long,
    val receivedBytes: Long
) : NetworkStatisticsEntry
