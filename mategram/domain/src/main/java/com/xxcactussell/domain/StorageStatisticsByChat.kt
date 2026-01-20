package com.xxcactussell.domain

data class StorageStatisticsByChat(
    val chatId: Long,
    val size: Long,
    val count: Int,
    val byFileType: List<StorageStatisticsByFileType>
) : Object
