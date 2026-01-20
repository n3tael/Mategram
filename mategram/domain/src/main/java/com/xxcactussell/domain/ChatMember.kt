package com.xxcactussell.domain

data class ChatMember(
    val memberId: MessageSender,
    val inviterUserId: Long,
    val joinedChatDate: Int,
    val status: ChatMemberStatus
) : Object
