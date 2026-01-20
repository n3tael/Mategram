package com.xxcactussell.domain

data class LocalFile(
    val path: String,
    val canBeDownloaded: Boolean,
    val canBeDeleted: Boolean,
    val isDownloadingActive: Boolean,
    val isDownloadingCompleted: Boolean,
    val downloadOffset: Long,
    val downloadedPrefixSize: Long,
    val downloadedSize: Long
) : Object
