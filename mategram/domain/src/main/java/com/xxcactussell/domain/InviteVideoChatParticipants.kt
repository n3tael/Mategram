package com.xxcactussell.domain

data class InviteVideoChatParticipants(
    val groupCallId: Int,
    val userIds: LongArray
) : Function
