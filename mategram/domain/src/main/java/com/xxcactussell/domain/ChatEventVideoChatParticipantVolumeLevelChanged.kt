package com.xxcactussell.domain

data class ChatEventVideoChatParticipantVolumeLevelChanged(
    val participantId: MessageSender,
    val volumeLevel: Int
) : ChatEventAction
