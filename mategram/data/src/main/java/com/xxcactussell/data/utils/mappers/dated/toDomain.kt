package com.xxcactussell.data.utils.mappers.dated

import com.xxcactussell.data.utils.mappers.file.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.DatedFile.toDomain(): DatedFile = DatedFile(
    file = this.file.toDomain(),
    date = this.date
)

