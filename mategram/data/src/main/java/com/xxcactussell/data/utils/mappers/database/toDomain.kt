package com.xxcactussell.data.utils.mappers.database

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.DatabaseStatistics.toDomain(): DatabaseStatistics = DatabaseStatistics(
    statistics = this.statistics
)

