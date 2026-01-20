package com.xxcactussell.data.utils.mappers.minithumbnail

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.Minithumbnail.toDomain(): Minithumbnail = Minithumbnail(
    width = this.width,
    height = this.height,
    data = this.data
)

