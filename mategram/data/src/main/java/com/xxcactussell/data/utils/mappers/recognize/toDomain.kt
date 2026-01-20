package com.xxcactussell.data.utils.mappers.recognize

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.RecognizeSpeech.toDomain(): RecognizeSpeech = RecognizeSpeech(
    chatId = this.chatId,
    messageId = this.messageId
)

