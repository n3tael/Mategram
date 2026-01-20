package com.xxcactussell.domain

data class SetChatDraftMessage(
    val chatId: Long,
    val messageThreadId: Long,
    val draftMessage: DraftMessage
) : Function
