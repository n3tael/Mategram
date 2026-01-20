package com.xxcactussell.domain

data class ReorderChatFolders(
    val chatFolderIds: IntArray,
    val mainChatListPosition: Int
) : Function
