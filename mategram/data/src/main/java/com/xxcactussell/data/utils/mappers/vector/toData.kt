package com.xxcactussell.data.utils.mappers.vector

import com.xxcactussell.data.utils.mappers.point.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun VectorPathCommand.toData(): TdApi.VectorPathCommand = when(this) {
    is VectorPathCommandLine -> this.toData()
    is VectorPathCommandCubicBezierCurve -> this.toData()
}

fun VectorPathCommandCubicBezierCurve.toData(): TdApi.VectorPathCommandCubicBezierCurve = TdApi.VectorPathCommandCubicBezierCurve(
    this.startControlPoint.toData(),
    this.endControlPoint.toData(),
    this.endPoint.toData()
)

fun VectorPathCommandLine.toData(): TdApi.VectorPathCommandLine = TdApi.VectorPathCommandLine(
    this.endPoint.toData()
)

