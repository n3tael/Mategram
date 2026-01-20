package com.xxcactussell.data.utils.mappers.optimize

import com.xxcactussell.data.utils.mappers.file.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.OptimizeStorage.toDomain(): OptimizeStorage = OptimizeStorage(
    size = this.size,
    ttl = this.ttl,
    count = this.count,
    immunityDelay = this.immunityDelay,
    fileTypes = this.fileTypes.map { it.toDomain() },
    chatIds = this.chatIds,
    excludeChatIds = this.excludeChatIds,
    returnDeletedFileStatistics = this.returnDeletedFileStatistics,
    chatLimit = this.chatLimit
)

