package com.xxcactussell.domain

data class InternalLinkTypeVideoChat(
    val chatUsername: String,
    val inviteHash: String,
    val isLiveStream: Boolean
) : InternalLinkType
