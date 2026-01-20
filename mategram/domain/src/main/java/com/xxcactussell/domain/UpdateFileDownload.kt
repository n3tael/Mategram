package com.xxcactussell.domain

data class UpdateFileDownload(
    val fileId: Int,
    val completeDate: Int,
    val isPaused: Boolean,
    val counts: DownloadedFileCounts
) : Update
