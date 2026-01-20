package com.xxcactussell.domain

data class LinkPreviewTypeChat(
    val type: InviteLinkChatType,
    val photo: ChatPhoto? = null,
    val createsJoinRequest: Boolean
) : LinkPreviewType
