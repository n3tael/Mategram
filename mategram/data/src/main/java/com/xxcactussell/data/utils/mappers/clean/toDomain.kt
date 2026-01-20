package com.xxcactussell.data.utils.mappers.clean

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.CleanFileName.toDomain(): CleanFileName = CleanFileName(
    fileName = this.fileName
)

