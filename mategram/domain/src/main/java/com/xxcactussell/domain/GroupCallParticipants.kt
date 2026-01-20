package com.xxcactussell.domain

data class GroupCallParticipants(
    val totalCount: Int,
    val participantIds: List<MessageSender>
) : Object
