package com.xxcactussell.data.utils.mappers.write

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.WriteGeneratedFilePart.toDomain(): WriteGeneratedFilePart = WriteGeneratedFilePart(
    generationId = this.generationId,
    offset = this.offset,
    data = this.data
)

