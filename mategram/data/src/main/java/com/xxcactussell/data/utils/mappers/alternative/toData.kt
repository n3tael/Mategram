package com.xxcactussell.data.utils.mappers.alternative

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*
import com.xxcactussell.data.utils.mappers.file.toData

// Mappers: Domain -> TdApi

fun AlternativeVideo.toData(): TdApi.AlternativeVideo = TdApi.AlternativeVideo(
    this.id,
    this.width,
    this.height,
    this.codec,
    this.hlsFile.toData(),
    this.video.toData()
)

