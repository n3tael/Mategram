package com.xxcactussell.domain

data class FoundFileDownloads(
    val totalCounts: DownloadedFileCounts,
    val files: List<FileDownload>,
    val nextOffset: String
) : Object
