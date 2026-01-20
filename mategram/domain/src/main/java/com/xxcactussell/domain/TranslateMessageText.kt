package com.xxcactussell.domain

data class TranslateMessageText(
    val chatId: Long,
    val messageId: Long,
    val toLanguageCode: String
) : Function
