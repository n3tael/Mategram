package com.xxcactussell.domain

data class EditChatSubscriptionInviteLink(
    val chatId: Long,
    val inviteLink: String,
    val name: String
) : Function
