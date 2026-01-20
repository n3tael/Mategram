package com.xxcactussell.data.utils.mappers.rate

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.RateSpeechRecognition.toDomain(): RateSpeechRecognition = RateSpeechRecognition(
    chatId = this.chatId,
    messageId = this.messageId,
    isGood = this.isGood
)

