package com.xxcactussell.domain

data class GetCallbackQueryMessage(
    val chatId: Long,
    val messageId: Long,
    val callbackQueryId: Long
) : Function
