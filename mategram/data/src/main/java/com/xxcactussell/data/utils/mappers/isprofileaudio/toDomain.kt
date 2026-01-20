package com.xxcactussell.data.utils.mappers.isprofileaudio

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.IsProfileAudio.toDomain(): IsProfileAudio = IsProfileAudio(
    fileId = this.fileId
)

