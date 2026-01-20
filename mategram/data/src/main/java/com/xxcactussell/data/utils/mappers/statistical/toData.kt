package com.xxcactussell.data.utils.mappers.statistical

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun StatisticalGraph.toData(): TdApi.StatisticalGraph = when(this) {
    is StatisticalGraphData -> this.toData()
    is StatisticalGraphAsync -> this.toData()
    is StatisticalGraphError -> this.toData()
}

fun StatisticalGraphAsync.toData(): TdApi.StatisticalGraphAsync = TdApi.StatisticalGraphAsync(
    this.token
)

fun StatisticalGraphData.toData(): TdApi.StatisticalGraphData = TdApi.StatisticalGraphData(
    this.jsonData,
    this.zoomToken
)

fun StatisticalGraphError.toData(): TdApi.StatisticalGraphError = TdApi.StatisticalGraphError(
    this.errorMessage
)

fun StatisticalValue.toData(): TdApi.StatisticalValue = TdApi.StatisticalValue(
    this.value,
    this.previousValue,
    this.growthRatePercentage
)

