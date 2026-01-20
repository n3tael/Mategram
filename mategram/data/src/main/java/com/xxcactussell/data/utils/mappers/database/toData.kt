package com.xxcactussell.data.utils.mappers.database

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun DatabaseStatistics.toData(): TdApi.DatabaseStatistics = TdApi.DatabaseStatistics(
    this.statistics
)

