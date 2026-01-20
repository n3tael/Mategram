package com.xxcactussell.domain

data class ChatEventMemberInvited(
    val userId: Long,
    val status: ChatMemberStatus
) : ChatEventAction
