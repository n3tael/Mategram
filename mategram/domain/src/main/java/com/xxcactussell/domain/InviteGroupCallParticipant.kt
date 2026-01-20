package com.xxcactussell.domain

data class InviteGroupCallParticipant(
    val groupCallId: Int,
    val userId: Long,
    val isVideo: Boolean
) : Function
