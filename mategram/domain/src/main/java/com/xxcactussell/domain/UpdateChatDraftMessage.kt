package com.xxcactussell.domain

data class UpdateChatDraftMessage(
    val chatId: Long,
    val draftMessage: DraftMessage? = null,
    val positions: List<ChatPosition>
) : Update
