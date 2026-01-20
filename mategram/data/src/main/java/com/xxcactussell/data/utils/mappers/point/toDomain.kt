package com.xxcactussell.data.utils.mappers.point

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.Point.toDomain(): Point = Point(
    x = this.x,
    y = this.y
)

