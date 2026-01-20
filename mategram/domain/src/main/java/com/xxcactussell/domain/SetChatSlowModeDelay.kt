package com.xxcactussell.domain

data class SetChatSlowModeDelay(
    val chatId: Long,
    val slowModeDelay: Int
) : Function
