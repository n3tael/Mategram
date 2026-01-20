package com.xxcactussell.domain

data class UpdateChatMessageAutoDeleteTime(
    val chatId: Long,
    val messageAutoDeleteTime: Int
) : Update
