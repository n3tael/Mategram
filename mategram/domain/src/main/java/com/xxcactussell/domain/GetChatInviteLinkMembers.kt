package com.xxcactussell.domain

data class GetChatInviteLinkMembers(
    val chatId: Long,
    val inviteLink: String,
    val onlyWithExpiredSubscription: Boolean,
    val offsetMember: ChatInviteLinkMember,
    val limit: Int
) : Function
