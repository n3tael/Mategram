package com.xxcactussell.domain

data class ToggleVideoChatMuteNewParticipants(
    val groupCallId: Int,
    val muteNewParticipants: Boolean
) : Function
