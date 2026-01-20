package com.xxcactussell.domain

data class ToggleGroupCallParticipantIsHandRaised(
    val groupCallId: Int,
    val participantId: MessageSender,
    val isHandRaised: Boolean
) : Function
