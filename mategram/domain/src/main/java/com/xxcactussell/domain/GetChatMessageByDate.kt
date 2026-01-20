package com.xxcactussell.domain

data class GetChatMessageByDate(
    val chatId: Long,
    val date: Int
) : Function
