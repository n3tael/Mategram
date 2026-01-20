package com.xxcactussell.domain

data class ChatEventInviteLinkRevoked(
    val inviteLink: ChatInviteLink
) : ChatEventAction
