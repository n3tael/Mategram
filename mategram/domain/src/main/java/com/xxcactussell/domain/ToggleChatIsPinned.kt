package com.xxcactussell.domain

data class ToggleChatIsPinned(
    val chatList: ChatList,
    val chatId: Long,
    val isPinned: Boolean
) : Function
