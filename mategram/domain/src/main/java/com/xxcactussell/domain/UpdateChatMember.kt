package com.xxcactussell.domain

data class UpdateChatMember(
    val chatId: Long,
    val actorUserId: Long,
    val date: Int,
    val inviteLink: ChatInviteLink? = null,
    val viaJoinRequest: Boolean,
    val viaChatFolderInviteLink: Boolean,
    val oldChatMember: ChatMember,
    val newChatMember: ChatMember
) : Update
