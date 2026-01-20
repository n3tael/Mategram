package com.xxcactussell.domain

data class ChatMemberStatusRestricted(
    val isMember: Boolean,
    val restrictedUntilDate: Int,
    val permissions: ChatPermissions
) : ChatMemberStatus
