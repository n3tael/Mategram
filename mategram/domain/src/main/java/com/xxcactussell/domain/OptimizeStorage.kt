package com.xxcactussell.domain

data class OptimizeStorage(
    val size: Long,
    val ttl: Int,
    val count: Int,
    val immunityDelay: Int,
    val fileTypes: List<FileType>,
    val chatIds: LongArray,
    val excludeChatIds: LongArray,
    val returnDeletedFileStatistics: Boolean,
    val chatLimit: Int
) : Function
