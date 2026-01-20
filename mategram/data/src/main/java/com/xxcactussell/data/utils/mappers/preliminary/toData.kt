package com.xxcactussell.data.utils.mappers.preliminary

import com.xxcactussell.data.utils.mappers.file.toData
import com.xxcactussell.data.utils.mappers.input.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun PreliminaryUploadFile.toData(): TdApi.PreliminaryUploadFile = TdApi.PreliminaryUploadFile(
    this.file.toData(),
    this.fileType.toData(),
    this.priority
)

