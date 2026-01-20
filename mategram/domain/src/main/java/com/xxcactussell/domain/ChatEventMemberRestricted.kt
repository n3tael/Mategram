package com.xxcactussell.domain

data class ChatEventMemberRestricted(
    val memberId: MessageSender,
    val oldStatus: ChatMemberStatus,
    val newStatus: ChatMemberStatus
) : ChatEventAction
