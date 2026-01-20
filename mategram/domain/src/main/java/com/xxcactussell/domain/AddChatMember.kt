package com.xxcactussell.domain

data class AddChatMember(
    val chatId: Long,
    val userId: Long,
    val forwardLimit: Int
) : Function
