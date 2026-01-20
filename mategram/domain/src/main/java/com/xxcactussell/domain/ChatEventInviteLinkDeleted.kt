package com.xxcactussell.domain

data class ChatEventInviteLinkDeleted(
    val inviteLink: ChatInviteLink
) : ChatEventAction
