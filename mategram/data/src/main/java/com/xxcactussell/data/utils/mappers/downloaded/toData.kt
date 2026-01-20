package com.xxcactussell.data.utils.mappers.downloaded

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun DownloadedFileCounts.toData(): TdApi.DownloadedFileCounts = TdApi.DownloadedFileCounts(
    this.activeCount,
    this.pausedCount,
    this.completedCount
)

