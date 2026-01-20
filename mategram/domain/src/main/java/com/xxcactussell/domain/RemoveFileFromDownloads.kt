package com.xxcactussell.domain

data class RemoveFileFromDownloads(
    val fileId: Int,
    val deleteFromCache: Boolean
) : Function
