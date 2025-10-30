package com.xxcactussell.presentation.chats.model

sealed interface ChatsEvent {
    data class OnChatSwipedLeft(val chatId: Long) : ChatsEvent
    data class OnChatSwipedRight(val chatId: Long) : ChatsEvent
    data class OnFolderChanged(val folderId: Int) : ChatsEvent
}