package com.xxcactussell.domain

data class RateSpeechRecognition(
    val chatId: Long,
    val messageId: Long,
    val isGood: Boolean
) : Function
