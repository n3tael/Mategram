package com.xxcactussell.domain

data class GroupCallParticipantVideoInfo(
    val sourceGroups: List<GroupCallVideoSourceGroup>,
    val endpointId: String,
    val isPaused: Boolean
) : Object
