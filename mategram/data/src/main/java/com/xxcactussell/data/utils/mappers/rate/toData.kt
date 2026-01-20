package com.xxcactussell.data.utils.mappers.rate

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun RateSpeechRecognition.toData(): TdApi.RateSpeechRecognition = TdApi.RateSpeechRecognition(
    this.chatId,
    this.messageId,
    this.isGood
)

