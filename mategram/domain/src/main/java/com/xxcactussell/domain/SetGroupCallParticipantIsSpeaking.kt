package com.xxcactussell.domain

data class SetGroupCallParticipantIsSpeaking(
    val groupCallId: Int,
    val audioSource: Int,
    val isSpeaking: Boolean
) : Function
