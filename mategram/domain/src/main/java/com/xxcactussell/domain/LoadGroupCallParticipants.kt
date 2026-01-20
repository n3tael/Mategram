package com.xxcactussell.domain

data class LoadGroupCallParticipants(
    val groupCallId: Int,
    val limit: Int
) : Function
