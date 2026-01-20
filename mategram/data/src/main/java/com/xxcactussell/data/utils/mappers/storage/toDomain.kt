package com.xxcactussell.data.utils.mappers.storage

import com.xxcactussell.data.utils.mappers.file.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.StorageStatistics.toDomain(): StorageStatistics = StorageStatistics(
    size = this.size,
    count = this.count,
    byChat = this.byChat.map { it.toDomain() }
)

fun TdApi.StorageStatisticsByChat.toDomain(): StorageStatisticsByChat = StorageStatisticsByChat(
    chatId = this.chatId,
    size = this.size,
    count = this.count,
    byFileType = this.byFileType.map { it.toDomain() }
)

fun TdApi.StorageStatisticsByFileType.toDomain(): StorageStatisticsByFileType = StorageStatisticsByFileType(
    fileType = this.fileType.toDomain(),
    size = this.size,
    count = this.count
)

fun TdApi.StorageStatisticsFast.toDomain(): StorageStatisticsFast = StorageStatisticsFast(
    filesSize = this.filesSize,
    fileCount = this.fileCount,
    databaseSize = this.databaseSize,
    languagePackDatabaseSize = this.languagePackDatabaseSize,
    logSize = this.logSize
)

