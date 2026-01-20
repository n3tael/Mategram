package com.xxcactussell.domain

data class SetChatMessageAutoDeleteTime(
    val chatId: Long,
    val messageAutoDeleteTime: Int
) : Function
