package com.xxcactussell.domain

data class UpdateChatAvailableReactions(
    val chatId: Long,
    val availableReactions: ChatAvailableReactions
) : Update
