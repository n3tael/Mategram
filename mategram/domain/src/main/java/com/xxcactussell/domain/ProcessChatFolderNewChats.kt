package com.xxcactussell.domain

data class ProcessChatFolderNewChats(
    val chatFolderId: Int,
    val addedChatIds: LongArray
) : Function
