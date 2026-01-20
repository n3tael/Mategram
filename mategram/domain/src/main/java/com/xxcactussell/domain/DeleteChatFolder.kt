package com.xxcactussell.domain

data class DeleteChatFolder(
    val chatFolderId: Int,
    val leaveChatIds: LongArray
) : Function
