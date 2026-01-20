package com.xxcactussell.domain

data class GetChatInviteLink(
    val chatId: Long,
    val inviteLink: String
) : Function
