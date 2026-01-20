package com.xxcactussell.data.utils.mappers.outline

import com.xxcactussell.data.utils.mappers.auth.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.Outline.toDomain(): Outline = Outline(
    paths = this.paths.map { it.toDomain() }
)

