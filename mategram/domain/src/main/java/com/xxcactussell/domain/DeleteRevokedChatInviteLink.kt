package com.xxcactussell.domain

data class DeleteRevokedChatInviteLink(
    val chatId: Long,
    val inviteLink: String
) : Function
