package com.xxcactussell.domain

data class RevokeChatInviteLink(
    val chatId: Long,
    val inviteLink: String
) : Function
