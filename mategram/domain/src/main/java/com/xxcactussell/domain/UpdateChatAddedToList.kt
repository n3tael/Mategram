package com.xxcactussell.domain

data class UpdateChatAddedToList(
    val chatId: Long,
    val chatList: ChatList
) : Update
