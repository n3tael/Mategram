package com.xxcactussell.data.utils.mappers.animation

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*
import com.xxcactussell.data.utils.mappers.file.toData
import com.xxcactussell.data.utils.mappers.minithumbnail.toData

fun Animation.toData(): TdApi.Animation = TdApi.Animation(
    this.duration,
    this.width,
    this.height,
    this.fileName,
    this.mimeType,
    this.hasStickers,
    this.minithumbnail?.toData(),
    this.thumbnail?.toData(),
    this.animation.toData()
)

