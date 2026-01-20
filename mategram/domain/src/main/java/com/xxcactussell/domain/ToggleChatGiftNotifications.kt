package com.xxcactussell.domain

data class ToggleChatGiftNotifications(
    val chatId: Long,
    val areEnabled: Boolean
) : Function
