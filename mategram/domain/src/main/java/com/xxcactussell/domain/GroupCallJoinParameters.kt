package com.xxcactussell.domain

data class GroupCallJoinParameters(
    val audioSourceId: Int,
    val payload: String,
    val isMuted: Boolean,
    val isMyVideoEnabled: Boolean
) : Object
