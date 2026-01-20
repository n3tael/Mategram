package com.xxcactussell.data.utils.mappers.vector

import com.xxcactussell.data.utils.mappers.point.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.VectorPathCommand.toDomain(): VectorPathCommand = when(this) {
    is TdApi.VectorPathCommandLine -> this.toDomain()
    is TdApi.VectorPathCommandCubicBezierCurve -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.VectorPathCommandCubicBezierCurve.toDomain(): VectorPathCommandCubicBezierCurve = VectorPathCommandCubicBezierCurve(
    startControlPoint = this.startControlPoint.toDomain(),
    endControlPoint = this.endControlPoint.toDomain(),
    endPoint = this.endPoint.toDomain()
)

fun TdApi.VectorPathCommandLine.toDomain(): VectorPathCommandLine = VectorPathCommandLine(
    endPoint = this.endPoint.toDomain()
)

