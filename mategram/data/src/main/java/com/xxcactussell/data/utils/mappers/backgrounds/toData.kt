package com.xxcactussell.data.utils.mappers.backgrounds

import com.xxcactussell.data.utils.mappers.background.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun Backgrounds.toData(): TdApi.Backgrounds = TdApi.Backgrounds(
    this.backgrounds.map { it.toData() }.toTypedArray()
)

