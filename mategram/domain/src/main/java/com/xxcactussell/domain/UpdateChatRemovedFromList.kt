package com.xxcactussell.domain

data class UpdateChatRemovedFromList(
    val chatId: Long,
    val chatList: ChatList
) : Update
