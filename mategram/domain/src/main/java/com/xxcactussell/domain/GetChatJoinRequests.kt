package com.xxcactussell.domain

data class GetChatJoinRequests(
    val chatId: Long,
    val inviteLink: String,
    val query: String,
    val offsetRequest: ChatJoinRequest,
    val limit: Int
) : Function
