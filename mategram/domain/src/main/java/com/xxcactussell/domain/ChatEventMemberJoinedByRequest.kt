package com.xxcactussell.domain

data class ChatEventMemberJoinedByRequest(
    val approverUserId: Long,
    val inviteLink: ChatInviteLink? = null
) : ChatEventAction
