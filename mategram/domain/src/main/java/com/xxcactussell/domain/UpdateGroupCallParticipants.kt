package com.xxcactussell.domain

data class UpdateGroupCallParticipants(
    val groupCallId: Int,
    val participantUserIds: LongArray
) : Update
