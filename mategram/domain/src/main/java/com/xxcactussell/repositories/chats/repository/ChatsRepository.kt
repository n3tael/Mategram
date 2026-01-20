package com.xxcactussell.repositories.chats.repository

import com.xxcactussell.domain.Chat
import com.xxcactussell.domain.ChatFolderInfo
import com.xxcactussell.domain.User
import com.xxcactussell.domain.UserStatus
import com.xxcactussell.repositories.chats.model.ChatStatus
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ChatsRepository {
    fun observeChatFolders(): Flow<List<ChatFolderInfo>>
    fun observeMainChatListPosition(): Flow<Int>

    fun observeChats(): Flow<List<Chat>>

    fun loadChats(folderId: Int = -1, limit: Int)
    fun observeChatStatuses(): Flow<Map<Long, UserStatus>>
    fun observeMe() : Flow<User?>
}

class ObserveChatsUpdateUseCase @Inject constructor(private val repository: ChatsRepository) {
    operator fun invoke(): Flow<List<Chat>> = repository.observeChats()
}
class ObserveChatStatusesUseCase @Inject constructor(private val repository: ChatsRepository) {
    operator fun invoke() : Flow<Map<Long, UserStatus>> = repository.observeChatStatuses()
}
class ObserveMeUseCase @Inject constructor(private val repository: ChatsRepository) {
    operator fun invoke(): Flow<User?> = repository.observeMe()
}
class LoadChatsUseCase @Inject constructor(private val repository: ChatsRepository) {
    operator fun invoke(folderId: Int = -1, limit: Int) = repository.loadChats(folderId, limit)
}
class ObserveChatFoldersUseCase @Inject constructor(private val repository: ChatsRepository) {
    operator fun invoke() : Flow<List<ChatFolderInfo>> = repository.observeChatFolders()
}