package com.xxcactussell.data.utils.mappers.mask

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.MaskPoint.toDomain(): MaskPoint = when(this) {
    is TdApi.MaskPointForehead -> this.toDomain()
    is TdApi.MaskPointEyes -> this.toDomain()
    is TdApi.MaskPointMouth -> this.toDomain()
    is TdApi.MaskPointChin -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.MaskPointChin.toDomain(): MaskPointChin = MaskPointChin

fun TdApi.MaskPointEyes.toDomain(): MaskPointEyes = MaskPointEyes

fun TdApi.MaskPointForehead.toDomain(): MaskPointForehead = MaskPointForehead

fun TdApi.MaskPointMouth.toDomain(): MaskPointMouth = MaskPointMouth

fun TdApi.MaskPosition.toDomain(): MaskPosition = MaskPosition(
    point = this.point.toDomain(),
    xShift = this.xShift,
    yShift = this.yShift,
    scale = this.scale
)

