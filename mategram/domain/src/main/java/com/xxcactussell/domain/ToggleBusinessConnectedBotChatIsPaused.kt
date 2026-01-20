package com.xxcactussell.domain

data class ToggleBusinessConnectedBotChatIsPaused(
    val chatId: Long,
    val isPaused: Boolean
) : Function
