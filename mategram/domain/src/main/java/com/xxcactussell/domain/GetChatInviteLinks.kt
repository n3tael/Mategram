package com.xxcactussell.domain

data class GetChatInviteLinks(
    val chatId: Long,
    val creatorUserId: Long,
    val isRevoked: Boolean,
    val offsetDate: Int,
    val offsetInviteLink: String,
    val limit: Int
) : Function
