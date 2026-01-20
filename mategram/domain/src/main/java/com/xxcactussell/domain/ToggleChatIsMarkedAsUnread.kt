package com.xxcactussell.domain

data class ToggleChatIsMarkedAsUnread(
    val chatId: Long,
    val isMarkedAsUnread: Boolean
) : Function
