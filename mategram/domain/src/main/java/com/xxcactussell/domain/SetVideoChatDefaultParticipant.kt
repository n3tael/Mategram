package com.xxcactussell.domain

data class SetVideoChatDefaultParticipant(
    val chatId: Long,
    val defaultParticipantId: MessageSender
) : Function
