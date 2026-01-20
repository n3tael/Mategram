package com.xxcactussell.data.utils.mappers.animations

import com.xxcactussell.data.utils.mappers.animation.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.Animations.toDomain(): Animations = Animations(
    animations = this.animations.map { it.toDomain() }
)

