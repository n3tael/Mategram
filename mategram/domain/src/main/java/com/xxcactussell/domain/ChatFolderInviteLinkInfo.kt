package com.xxcactussell.domain

data class ChatFolderInviteLinkInfo(
    val chatFolderInfo: ChatFolderInfo,
    val missingChatIds: LongArray,
    val addedChatIds: LongArray
) : Object
