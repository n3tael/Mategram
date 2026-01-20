package com.xxcactussell.data.utils.mappers.mask

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun MaskPoint.toData(): TdApi.MaskPoint = when(this) {
    is MaskPointForehead -> this.toData()
    is MaskPointEyes -> this.toData()
    is MaskPointMouth -> this.toData()
    is MaskPointChin -> this.toData()
}

fun MaskPointChin.toData(): TdApi.MaskPointChin = TdApi.MaskPointChin(
)

fun MaskPointEyes.toData(): TdApi.MaskPointEyes = TdApi.MaskPointEyes(
)

fun MaskPointForehead.toData(): TdApi.MaskPointForehead = TdApi.MaskPointForehead(
)

fun MaskPointMouth.toData(): TdApi.MaskPointMouth = TdApi.MaskPointMouth(
)

fun MaskPosition.toData(): TdApi.MaskPosition = TdApi.MaskPosition(
    this.point.toData(),
    this.xShift,
    this.yShift,
    this.scale
)

