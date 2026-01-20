package com.xxcactussell.domain

data class SpeechRecognitionResultPending(
    val partialText: String
) : SpeechRecognitionResult
