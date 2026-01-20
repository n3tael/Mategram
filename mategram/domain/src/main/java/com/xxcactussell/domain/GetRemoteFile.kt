package com.xxcactussell.domain

data class GetRemoteFile(
    val remoteFileId: String,
    val fileType: FileType
) : Function
