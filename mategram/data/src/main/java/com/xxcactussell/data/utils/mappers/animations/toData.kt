package com.xxcactussell.data.utils.mappers.animations

import com.xxcactussell.data.utils.mappers.animation.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun Animations.toData(): TdApi.Animations = TdApi.Animations(
    this.animations.map { it.toData() }.toTypedArray()
)

