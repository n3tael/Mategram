package com.xxcactussell.domain

data class UpdateFileDownloads(
    val totalSize: Long,
    val totalCount: Int,
    val downloadedSize: Long
) : Update
