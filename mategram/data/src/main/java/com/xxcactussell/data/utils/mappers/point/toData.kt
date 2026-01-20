package com.xxcactussell.data.utils.mappers.point

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun Point.toData(): TdApi.Point = TdApi.Point(
    this.x,
    this.y
)

