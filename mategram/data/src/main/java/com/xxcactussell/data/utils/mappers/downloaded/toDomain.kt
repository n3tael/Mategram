package com.xxcactussell.data.utils.mappers.downloaded

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.DownloadedFileCounts.toDomain(): DownloadedFileCounts = DownloadedFileCounts(
    activeCount = this.activeCount,
    pausedCount = this.pausedCount,
    completedCount = this.completedCount
)

