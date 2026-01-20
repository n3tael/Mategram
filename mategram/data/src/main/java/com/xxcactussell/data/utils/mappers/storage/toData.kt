package com.xxcactussell.data.utils.mappers.storage

import com.xxcactussell.data.utils.mappers.file.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun StorageStatistics.toData(): TdApi.StorageStatistics = TdApi.StorageStatistics(
    this.size,
    this.count,
    this.byChat.map { it.toData() }.toTypedArray()
)

fun StorageStatisticsByChat.toData(): TdApi.StorageStatisticsByChat = TdApi.StorageStatisticsByChat(
    this.chatId,
    this.size,
    this.count,
    this.byFileType.map { it.toData() }.toTypedArray()
)

fun StorageStatisticsByFileType.toData(): TdApi.StorageStatisticsByFileType = TdApi.StorageStatisticsByFileType(
    this.fileType.toData(),
    this.size,
    this.count
)

fun StorageStatisticsFast.toData(): TdApi.StorageStatisticsFast = TdApi.StorageStatisticsFast(
    this.filesSize,
    this.fileCount,
    this.databaseSize,
    this.languagePackDatabaseSize,
    this.logSize
)

