package com.xxcactussell.domain

data class UpdateGroupCallParticipant(
    val groupCallId: Int,
    val participant: GroupCallParticipant
) : Update
