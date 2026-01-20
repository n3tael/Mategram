package com.xxcactussell.domain

data class MessageInviteVideoChatParticipants(
    val groupCallId: Int,
    val userIds: LongArray
) : MessageContent
