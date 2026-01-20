package com.xxcactussell.domain

data class File(
    val id: Int,
    val size: Long,
    val expectedSize: Long,
    val local: LocalFile,
    val remote: RemoteFile
) : Object
