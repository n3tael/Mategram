package com.xxcactussell.data.utils.mappers.backgrounds

import com.xxcactussell.data.utils.mappers.background.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.Backgrounds.toDomain(): Backgrounds = Backgrounds(
    backgrounds = this.backgrounds.map { it.toDomain() }
)

