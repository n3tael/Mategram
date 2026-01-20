package com.xxcactussell.domain

data class SpeechRecognitionResultError(
    val error: Error
) : SpeechRecognitionResult
