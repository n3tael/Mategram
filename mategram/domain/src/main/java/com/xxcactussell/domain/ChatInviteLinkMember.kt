package com.xxcactussell.domain

data class ChatInviteLinkMember(
    val userId: Long,
    val joinedChatDate: Int,
    val viaChatFolderInviteLink: Boolean,
    val approverUserId: Long
) : Object
