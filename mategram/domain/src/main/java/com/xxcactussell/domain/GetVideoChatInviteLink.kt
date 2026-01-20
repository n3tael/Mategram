package com.xxcactussell.domain

data class GetVideoChatInviteLink(
    val groupCallId: Int,
    val canSelfUnmute: Boolean
) : Function
