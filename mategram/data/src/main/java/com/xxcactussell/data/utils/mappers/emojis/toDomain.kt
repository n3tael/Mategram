package com.xxcactussell.data.utils.mappers.emojis

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.Emojis.toDomain(): Emojis = Emojis(
    emojis = this.emojis.toList()
)

