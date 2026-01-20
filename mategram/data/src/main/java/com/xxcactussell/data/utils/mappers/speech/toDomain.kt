package com.xxcactussell.data.utils.mappers.speech

import com.xxcactussell.data.utils.mappers.error.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.SpeechRecognitionResult.toDomain(): SpeechRecognitionResult = when(this) {
    is TdApi.SpeechRecognitionResultPending -> this.toDomain()
    is TdApi.SpeechRecognitionResultText -> this.toDomain()
    is TdApi.SpeechRecognitionResultError -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.SpeechRecognitionResultError.toDomain(): SpeechRecognitionResultError = SpeechRecognitionResultError(
    error = this.error.toDomain()
)

fun TdApi.SpeechRecognitionResultPending.toDomain(): SpeechRecognitionResultPending = SpeechRecognitionResultPending(
    partialText = this.partialText
)

fun TdApi.SpeechRecognitionResultText.toDomain(): SpeechRecognitionResultText = SpeechRecognitionResultText(
    text = this.text
)

