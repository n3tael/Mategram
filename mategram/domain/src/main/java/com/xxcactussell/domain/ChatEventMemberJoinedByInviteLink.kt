package com.xxcactussell.domain

data class ChatEventMemberJoinedByInviteLink(
    val inviteLink: ChatInviteLink,
    val viaChatFolderInviteLink: Boolean
) : ChatEventAction
