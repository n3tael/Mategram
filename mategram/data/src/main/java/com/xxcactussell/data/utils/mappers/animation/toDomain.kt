package com.xxcactussell.data.utils.mappers.animation

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*
import com.xxcactussell.data.utils.mappers.file.toDomain
import com.xxcactussell.data.utils.mappers.minithumbnail.toDomain

fun TdApi.Animation.toDomain(): Animation = Animation(
    duration = this.duration,
    width = this.width,
    height = this.height,
    fileName = this.fileName,
    mimeType = this.mimeType,
    hasStickers = this.hasStickers,
    minithumbnail = this.minithumbnail?.toDomain(),
    thumbnail = this.thumbnail?.toDomain(),
    animation = this.animation.toDomain()
)

