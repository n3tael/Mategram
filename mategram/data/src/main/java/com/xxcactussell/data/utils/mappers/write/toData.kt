package com.xxcactussell.data.utils.mappers.write

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun WriteGeneratedFilePart.toData(): TdApi.WriteGeneratedFilePart = TdApi.WriteGeneratedFilePart(
    this.generationId,
    this.offset,
    this.data
)

