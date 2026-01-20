package com.xxcactussell.domain

data class JoinVideoChat(
    val groupCallId: Int,
    val participantId: MessageSender,
    val joinParameters: GroupCallJoinParameters,
    val inviteHash: String
) : Function
