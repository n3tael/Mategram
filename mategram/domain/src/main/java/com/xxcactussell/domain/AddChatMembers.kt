package com.xxcactussell.domain

data class AddChatMembers(
    val chatId: Long,
    val userIds: LongArray
) : Function
