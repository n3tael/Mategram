package com.xxcactussell.data.utils

import com.xxcactussell.domain.chats.model.ChatList
import org.drinkless.tdlib.TdApi

fun TdApi.ChatList.toDomain() : ChatList {
    return when(this.constructor) {
        TdApi.ChatListMain.CONSTRUCTOR -> ChatList.Main()
        TdApi.ChatListFolder.CONSTRUCTOR -> ChatList.Folder((this as TdApi.ChatListFolder).chatFolderId)
        TdApi.ChatListArchive.CONSTRUCTOR -> ChatList.Archive()
        else -> ChatList.Main()
    }
}