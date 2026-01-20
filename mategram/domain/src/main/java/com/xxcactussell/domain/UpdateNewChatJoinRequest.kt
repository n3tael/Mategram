package com.xxcactussell.domain

data class UpdateNewChatJoinRequest(
    val chatId: Long,
    val request: ChatJoinRequest,
    val userChatId: Long,
    val inviteLink: ChatInviteLink? = null
) : Update
