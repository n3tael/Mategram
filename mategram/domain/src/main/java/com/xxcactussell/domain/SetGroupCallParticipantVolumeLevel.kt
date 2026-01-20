package com.xxcactussell.domain

data class SetGroupCallParticipantVolumeLevel(
    val groupCallId: Int,
    val participantId: MessageSender,
    val volumeLevel: Int
) : Function
