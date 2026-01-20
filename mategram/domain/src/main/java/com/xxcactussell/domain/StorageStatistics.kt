package com.xxcactussell.domain

data class StorageStatistics(
    val size: Long,
    val count: Int,
    val byChat: List<StorageStatisticsByChat>
) : Object
