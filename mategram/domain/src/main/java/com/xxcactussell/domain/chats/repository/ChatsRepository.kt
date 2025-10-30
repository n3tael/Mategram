package com.xxcactussell.domain.chats.repository

import com.xxcactussell.domain.chats.model.Chat
import com.xxcactussell.domain.chats.model.ChatFolder
import com.xxcactussell.domain.chats.model.ChatStatus
import com.xxcactussell.domain.chats.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ChatsRepository {
    fun observeChatFolders(): Flow<List<ChatFolder>>
    fun observeMainChatListPosition(): Flow<Int>
    fun observeSortedChats(): Flow<Map<Int, List<Chat>>>
    fun loadChats(folderId: Int = -1, limit: Int)
    fun observeChatStatuses(): Flow<Map<Long, ChatStatus>>
    fun observeMe() : Flow<User?>
}

class ObserveChatStatusesUseCase @Inject constructor(private val repository: ChatsRepository) {
    operator fun invoke() : Flow<Map<Long, ChatStatus>> = repository.observeChatStatuses()
}
class ObserveMeUseCase @Inject constructor(private val repository: ChatsRepository) {
    operator fun invoke(): Flow<User?> = repository.observeMe()
}

class ObserveChatsUpdateUseCase @Inject constructor(private val repository: ChatsRepository) {
    operator fun invoke(): Flow<Map<Int, List<Chat>>> = repository.observeSortedChats()
}

class LoadChatsUseCase @Inject constructor(private val repository: ChatsRepository) {
    operator fun invoke(folderId: Int = -1, limit: Int) = repository.loadChats(folderId, limit)
}

class ObserveChatFoldersUseCase @Inject constructor(private val repository: ChatsRepository) {
    operator fun invoke() : Flow<List<ChatFolder>> = repository.observeChatFolders()
}