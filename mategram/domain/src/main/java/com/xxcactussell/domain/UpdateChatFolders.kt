package com.xxcactussell.domain

data class UpdateChatFolders(
    val chatFolders: List<ChatFolderInfo>,
    val mainChatListPosition: Int,
    val areTagsEnabled: Boolean
) : Update
