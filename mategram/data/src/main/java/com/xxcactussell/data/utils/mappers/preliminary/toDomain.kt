package com.xxcactussell.data.utils.mappers.preliminary

import com.xxcactussell.data.utils.mappers.file.toDomain
import com.xxcactussell.data.utils.mappers.input.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.PreliminaryUploadFile.toDomain(): PreliminaryUploadFile = PreliminaryUploadFile(
    file = this.file.toDomain(),
    fileType = this.fileType.toDomain(),
    priority = this.priority
)

