package com.xxcactussell.domain

data class UpdateFileAddedToDownloads(
    val fileDownload: FileDownload,
    val counts: DownloadedFileCounts
) : Update
