package com.xxcactussell.domain

data class UpdateNewInlineCallbackQuery(
    val id: Long,
    val senderUserId: Long,
    val inlineMessageId: String,
    val chatInstance: Long,
    val payload: CallbackQueryPayload
) : Update
