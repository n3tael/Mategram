package com.xxcactussell.domain

data class SetChatAvailableReactions(
    val chatId: Long,
    val availableReactions: ChatAvailableReactions
) : Function
