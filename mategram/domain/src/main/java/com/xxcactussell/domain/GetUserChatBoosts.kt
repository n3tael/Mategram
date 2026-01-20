package com.xxcactussell.domain

data class GetUserChatBoosts(
    val chatId: Long,
    val userId: Long
) : Function
