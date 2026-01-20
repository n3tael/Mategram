package com.xxcactussell.domain

data class AddChatToList(
    val chatId: Long,
    val chatList: ChatList
) : Function
