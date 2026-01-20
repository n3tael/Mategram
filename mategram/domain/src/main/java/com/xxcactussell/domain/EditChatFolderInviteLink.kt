package com.xxcactussell.domain

data class EditChatFolderInviteLink(
    val chatFolderId: Int,
    val inviteLink: String,
    val name: String,
    val chatIds: LongArray
) : Function
