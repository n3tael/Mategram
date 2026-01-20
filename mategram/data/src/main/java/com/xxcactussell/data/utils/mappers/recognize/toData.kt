package com.xxcactussell.data.utils.mappers.recognize

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun RecognizeSpeech.toData(): TdApi.RecognizeSpeech = TdApi.RecognizeSpeech(
    this.chatId,
    this.messageId
)

