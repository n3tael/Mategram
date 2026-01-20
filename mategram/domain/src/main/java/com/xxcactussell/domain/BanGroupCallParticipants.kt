package com.xxcactussell.domain

data class BanGroupCallParticipants(
    val groupCallId: Int,
    val userIds: LongArray
) : Function
