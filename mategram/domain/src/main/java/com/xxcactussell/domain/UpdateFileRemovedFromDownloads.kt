package com.xxcactussell.domain

data class UpdateFileRemovedFromDownloads(
    val fileId: Int,
    val counts: DownloadedFileCounts
) : Update
