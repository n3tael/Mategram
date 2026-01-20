package com.xxcactussell.domain

data class ChatInviteLinkCount(
    val userId: Long,
    val inviteLinkCount: Int,
    val revokedInviteLinkCount: Int
) : Object
