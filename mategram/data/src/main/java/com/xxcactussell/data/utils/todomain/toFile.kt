package com.xxcactussell.data.utils

import com.xxcactussell.domain.files.model.File
import com.xxcactussell.domain.files.model.LocalFile
import com.xxcactussell.domain.files.model.RemoteFile
import org.drinkless.tdlib.TdApi

fun TdApi.File.toDomain() : File {
    val localFile = LocalFile(
        local.path,
        local.canBeDownloaded,
        local.canBeDeleted,
        local.isDownloadingActive,
        local.isDownloadingCompleted,
        local.downloadOffset,
        local.downloadedPrefixSize,
        local.downloadedSize
    )
    val remoteFile = RemoteFile(
        remote.id,
        remote.uniqueId,
        remote.isUploadingActive,
        remote.isUploadingCompleted,
        remote.uploadedSize
    )
    return File(id, size, expectedSize, localFile, remoteFile)
}