package com.xxcactussell.domain

data class ToggleGroupCallParticipantIsMuted(
    val groupCallId: Int,
    val participantId: MessageSender,
    val isMuted: Boolean
) : Function
