package com.xxcactussell.data.utils.mappers.outline

import com.xxcactussell.data.utils.mappers.auth.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun Outline.toData(): TdApi.Outline = TdApi.Outline(
    this.paths.map { it.toData() }.toTypedArray()
)

