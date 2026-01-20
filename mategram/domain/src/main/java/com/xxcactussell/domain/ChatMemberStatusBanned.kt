package com.xxcactussell.domain

data class ChatMemberStatusBanned(
    val bannedUntilDate: Int
) : ChatMemberStatus
