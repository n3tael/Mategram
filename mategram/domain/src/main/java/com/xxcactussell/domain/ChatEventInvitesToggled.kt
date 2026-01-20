package com.xxcactussell.domain

data class ChatEventInvitesToggled(
    val canInviteUsers: Boolean
) : ChatEventAction
