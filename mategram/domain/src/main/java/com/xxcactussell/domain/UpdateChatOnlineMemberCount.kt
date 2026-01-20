package com.xxcactussell.domain

data class UpdateChatOnlineMemberCount(
    val chatId: Long,
    val onlineMemberCount: Int
) : Update
