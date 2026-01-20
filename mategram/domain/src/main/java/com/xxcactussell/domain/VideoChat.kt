package com.xxcactussell.domain

data class VideoChat(
    val groupCallId: Int,
    val hasParticipants: Boolean,
    val defaultParticipantId: MessageSender? = null
) : Object
