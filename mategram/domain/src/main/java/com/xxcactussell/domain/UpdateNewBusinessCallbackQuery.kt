package com.xxcactussell.domain

data class UpdateNewBusinessCallbackQuery(
    val id: Long,
    val senderUserId: Long,
    val connectionId: String,
    val message: BusinessMessage,
    val chatInstance: Long,
    val payload: CallbackQueryPayload
) : Update
