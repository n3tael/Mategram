package com.xxcactussell.data.utils

import com.xxcactussell.data.utils.todomain.toDomain
import com.xxcactussell.domain.chats.model.ChatFolder
import com.xxcactussell.domain.chats.model.ChatFolderName
import org.drinkless.tdlib.TdApi

fun TdApi.ChatFolderInfo.toChatFolder() : ChatFolder {
    return  ChatFolder(ChatFolderName(this.name.text.toDomain(), this.name.animateCustomEmoji),0, this.id)
}