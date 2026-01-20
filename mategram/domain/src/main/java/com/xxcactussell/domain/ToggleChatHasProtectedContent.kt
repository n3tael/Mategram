package com.xxcactussell.domain

data class ToggleChatHasProtectedContent(
    val chatId: Long,
    val hasProtectedContent: Boolean
) : Function
