package com.xxcactussell.domain

data class ArchiveChatListSettings(
    val archiveAndMuteNewChatsFromUnknownUsers: Boolean,
    val keepUnmutedChatsArchived: Boolean,
    val keepChatsFromFoldersArchived: Boolean
) : Object
