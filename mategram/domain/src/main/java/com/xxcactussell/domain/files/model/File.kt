package com.xxcactussell.domain.files.model

data class LocalFile(
    val path: String,
    val canBeDownloaded: Boolean,
    val canBeDeleted: Boolean,
    val isDownloadingActive: Boolean,
    val isDownloadingComplete: Boolean,
    val downloadOffset: Long,
    val downloadPrefixSize: Long,
    val downloadedSize: Long
)

data class RemoteFile(
    val id: String,
    val uniqueId: String,
    val isUploadingActive: Boolean,
    val isUploadingComplete: Boolean,
    val uploadingSize: Long
)

data class File(
    val id: Int,
    val size: Long,
    val expectedSize: Long,
    val local: LocalFile,
    val remote: RemoteFile
)