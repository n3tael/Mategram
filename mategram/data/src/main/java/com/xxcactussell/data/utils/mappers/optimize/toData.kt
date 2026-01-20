package com.xxcactussell.data.utils.mappers.optimize

import com.xxcactussell.data.utils.mappers.file.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun OptimizeStorage.toData(): TdApi.OptimizeStorage = TdApi.OptimizeStorage(
    this.size,
    this.ttl,
    this.count,
    this.immunityDelay,
    this.fileTypes.map { it.toData() }.toTypedArray(),
    this.chatIds,
    this.excludeChatIds,
    this.returnDeletedFileStatistics,
    this.chatLimit
)

