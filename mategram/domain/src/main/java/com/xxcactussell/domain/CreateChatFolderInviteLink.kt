package com.xxcactussell.domain

data class CreateChatFolderInviteLink(
    val chatFolderId: Int,
    val name: String,
    val chatIds: LongArray
) : Function
