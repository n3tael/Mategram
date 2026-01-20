package com.xxcactussell.domain

data class MessageGroupCall(
    val isActive: Boolean,
    val wasMissed: Boolean,
    val isVideo: Boolean,
    val duration: Int,
    val otherParticipantIds: List<MessageSender>
) : MessageContent
