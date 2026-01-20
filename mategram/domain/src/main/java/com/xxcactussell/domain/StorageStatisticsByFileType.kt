package com.xxcactussell.domain

data class StorageStatisticsByFileType(
    val fileType: FileType,
    val size: Long,
    val count: Int
) : Object
