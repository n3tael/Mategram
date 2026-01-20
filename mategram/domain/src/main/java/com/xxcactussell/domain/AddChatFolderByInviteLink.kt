package com.xxcactussell.domain

data class AddChatFolderByInviteLink(
    val inviteLink: String,
    val chatIds: LongArray
) : Function
