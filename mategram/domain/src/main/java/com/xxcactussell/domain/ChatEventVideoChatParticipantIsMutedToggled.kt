package com.xxcactussell.domain

data class ChatEventVideoChatParticipantIsMutedToggled(
    val participantId: MessageSender,
    val isMuted: Boolean
) : ChatEventAction
