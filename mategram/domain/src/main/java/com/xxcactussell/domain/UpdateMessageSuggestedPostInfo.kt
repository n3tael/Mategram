package com.xxcactussell.domain

data class UpdateMessageSuggestedPostInfo(
    val chatId: Long,
    val messageId: Long,
    val suggestedPostInfo: SuggestedPostInfo
) : Update
