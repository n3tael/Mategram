package com.xxcactussell.data.utils.mappers.isprofileaudio

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun IsProfileAudio.toData(): TdApi.IsProfileAudio = TdApi.IsProfileAudio(
    this.fileId
)

