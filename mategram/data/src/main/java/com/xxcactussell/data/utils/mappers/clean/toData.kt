package com.xxcactussell.data.utils.mappers.clean

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun CleanFileName.toData(): TdApi.CleanFileName = TdApi.CleanFileName(
    this.fileName
)

