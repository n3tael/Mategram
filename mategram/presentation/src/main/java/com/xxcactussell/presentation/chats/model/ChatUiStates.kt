package com.xxcactussell.presentation.chats.model

import com.xxcactussell.domain.Chat
import com.xxcactussell.domain.ChatFolderName
import com.xxcactussell.domain.ChatPhotoInfo
import com.xxcactussell.domain.User

data class AvatarUiState(
    val chatId: Long,
    val photo: ChatPhotoInfo?,
    val title: String
)

data class TabInfoUi(
    val id: Int,
    val title: ChatFolderName?,
    val unreadCount: Int,
    val isSelected: Boolean
)

data class ChatListUiState(
    val allTabs: List<TabInfoUi> = emptyList(),
    val selectedIndex: Int = 0,
    val sortedChats: Map<Int, List<ChatItemUiState>> = emptyMap(),
    val selectedFolderId: Int = 0,
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val me: UserUiState? = null
)

data class ChatItemUiState(
    val chat: Chat,
    val isSelected: Boolean,
    val isUnread: Boolean,
    val photo: AvatarUiState? = null,
    val isPinned: Boolean,
    val isOnline: Boolean,
)

data class UserUiState(
    val user: User,
    val avatar: AvatarUiState
)