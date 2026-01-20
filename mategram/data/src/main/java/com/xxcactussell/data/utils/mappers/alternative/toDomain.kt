package com.xxcactussell.data.utils.mappers.alternative

import com.xxcactussell.data.utils.mappers.file.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.AlternativeVideo.toDomain(): AlternativeVideo = AlternativeVideo(
    id = this.id,
    width = this.width,
    height = this.height,
    codec = this.codec,
    hlsFile = this.hlsFile.toDomain(),
    video = this.video.toDomain()
)

