package com.xxcactussell.domain

data class GetCallbackQueryAnswer(
    val chatId: Long,
    val messageId: Long,
    val payload: CallbackQueryPayload
) : Function
