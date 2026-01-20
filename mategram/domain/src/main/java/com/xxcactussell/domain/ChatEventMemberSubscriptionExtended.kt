package com.xxcactussell.domain

data class ChatEventMemberSubscriptionExtended(
    val userId: Long,
    val oldStatus: ChatMemberStatus,
    val newStatus: ChatMemberStatus
) : ChatEventAction
