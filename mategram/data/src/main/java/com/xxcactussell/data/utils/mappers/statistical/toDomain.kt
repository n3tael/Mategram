package com.xxcactussell.data.utils.mappers.statistical

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.StatisticalGraph.toDomain(): StatisticalGraph = when(this) {
    is TdApi.StatisticalGraphData -> this.toDomain()
    is TdApi.StatisticalGraphAsync -> this.toDomain()
    is TdApi.StatisticalGraphError -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.StatisticalGraphAsync.toDomain(): StatisticalGraphAsync = StatisticalGraphAsync(
    token = this.token
)

fun TdApi.StatisticalGraphData.toDomain(): StatisticalGraphData = StatisticalGraphData(
    jsonData = this.jsonData,
    zoomToken = this.zoomToken
)

fun TdApi.StatisticalGraphError.toDomain(): StatisticalGraphError = StatisticalGraphError(
    errorMessage = this.errorMessage
)

fun TdApi.StatisticalValue.toDomain(): StatisticalValue = StatisticalValue(
    value = this.value,
    previousValue = this.previousValue,
    growthRatePercentage = this.growthRatePercentage
)

