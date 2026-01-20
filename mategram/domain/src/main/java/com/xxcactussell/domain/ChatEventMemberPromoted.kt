package com.xxcactussell.domain

data class ChatEventMemberPromoted(
    val userId: Long,
    val oldStatus: ChatMemberStatus,
    val newStatus: ChatMemberStatus
) : ChatEventAction
