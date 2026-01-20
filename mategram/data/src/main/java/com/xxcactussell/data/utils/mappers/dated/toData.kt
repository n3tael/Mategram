package com.xxcactussell.data.utils.mappers.dated

import com.xxcactussell.data.utils.mappers.file.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun DatedFile.toData(): TdApi.DatedFile = TdApi.DatedFile(
    this.file.toData(),
    this.date
)

