package com.xxcactussell.data.utils.mappers.speech

import com.xxcactussell.data.utils.mappers.error.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun SpeechRecognitionResult.toData(): TdApi.SpeechRecognitionResult = when(this) {
    is SpeechRecognitionResultPending -> this.toData()
    is SpeechRecognitionResultText -> this.toData()
    is SpeechRecognitionResultError -> this.toData()
}

fun SpeechRecognitionResultError.toData(): TdApi.SpeechRecognitionResultError = TdApi.SpeechRecognitionResultError(
    this.error.toData()
)

fun SpeechRecognitionResultPending.toData(): TdApi.SpeechRecognitionResultPending = TdApi.SpeechRecognitionResultPending(
    this.partialText
)

fun SpeechRecognitionResultText.toData(): TdApi.SpeechRecognitionResultText = TdApi.SpeechRecognitionResultText(
    this.text
)

