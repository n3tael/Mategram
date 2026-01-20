package com.xxcactussell.domain

data class ChatEventInviteLinkEdited(
    val oldInviteLink: ChatInviteLink,
    val newInviteLink: ChatInviteLink
) : ChatEventAction
