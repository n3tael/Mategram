package com.xxcactussell.domain

data class UpdateNewCallbackQuery(
    val id: Long,
    val senderUserId: Long,
    val chatId: Long,
    val messageId: Long,
    val chatInstance: Long,
    val payload: CallbackQueryPayload
) : Update
