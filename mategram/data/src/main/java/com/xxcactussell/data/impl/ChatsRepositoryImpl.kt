package com.xxcactussell.data.impl

import com.xxcactussell.data.TdClientManager
import com.xxcactussell.data.utils.mappers.chat.toDomain
import com.xxcactussell.data.utils.mappers.message.toDomain
import com.xxcactussell.data.utils.mappers.user.toDomain
import com.xxcactussell.domain.Chat
import com.xxcactussell.domain.ChatFolderInfo
import com.xxcactussell.domain.ChatList
import com.xxcactussell.domain.ChatListArchive
import com.xxcactussell.domain.ChatListFolder
import com.xxcactussell.domain.ChatListMain
import com.xxcactussell.domain.ChatPosition
import com.xxcactussell.domain.User
import com.xxcactussell.domain.UserStatus
import com.xxcactussell.repositories.chats.repository.ChatsRepository
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.sample
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.drinkless.tdlib.TdApi
import java.util.concurrent.ConcurrentHashMap

@OptIn(FlowPreview::class)
class ChatsRepositoryImpl @Inject constructor(
    private val clientManager: TdClientManager
) : ChatsRepository {

    private val chatsCache = ConcurrentHashMap<Long, Chat>()

    private val refreshTrigger = MutableSharedFlow<Unit>(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    private val _chatsFlow = MutableStateFlow<List<Chat>>(emptyList())
    private val _chatFolders = MutableStateFlow<List<ChatFolderInfo>>(emptyList())
    private val _mainChatListPosition = MutableStateFlow(0)
    private val _userStatuses = MutableStateFlow<Map<Long, UserStatus>>(emptyMap())
    private val _me = MutableStateFlow<User?>(null)

    private val repositoryScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    init {
        clientManager.send(TdApi.GetMe()) { result ->
            if (result is TdApi.User) {
                _me.value = result.toDomain()
            }
        }

        merge(
            clientManager.chatsUpdatesFlow,
            clientManager.chatFoldersUpdatesFlow
        )
            .buffer(Channel.UNLIMITED)
            .flowOn(Dispatchers.IO)
            .onEach { update ->
                withContext(Dispatchers.Default) {
                    handleChatUpdate(update)
                }
            }
            .launchIn(repositoryScope)

        refreshTrigger
            .sample(200L)
            .onEach {
                val allChats = withContext(Dispatchers.Default) {
                    chatsCache.values.toList()
                }
                _chatsFlow.value = allChats
            }
            .launchIn(repositoryScope)
    }

    override fun observeChats(): Flow<List<Chat>> = _chatsFlow.asStateFlow()

    private suspend fun handleChatUpdate(update: TdApi.Object) {
        var needRefresh = false

        when (update) {
            is TdApi.UpdateNewChat -> {
                chatsCache[update.chat.id] = update.chat.toDomain()
                needRefresh = true
            }
            is TdApi.UpdateChatPosition -> {
                val chat = chatsCache[update.chatId]
                if (chat != null) {
                    val newPositions = updateChatPositions(chat.positions, update.position)
                    if (newPositions != chat.positions) {
                        chatsCache[chat.id] = chat.copy(positions = newPositions)
                        needRefresh = true
                    }
                } else {
                    fetchChat(update.chatId)
                }
            }
            is TdApi.UpdateChatReadOutbox -> {
                chatsCache.computeIfPresent(update.chatId) { _, chat ->
                    chat.copy(lastReadOutboxMessageId = update.lastReadOutboxMessageId)
                }
            }
            is TdApi.UpdateChatLastMessage -> {
                chatsCache.computeIfPresent(update.chatId) { _, chat ->
                    chat.copy(lastMessage = update.lastMessage?.toDomain())
                } ?: run { fetchChat(update.chatId); null }
                needRefresh = true
            }
            is TdApi.UpdateChatReadInbox -> {
                chatsCache.computeIfPresent(update.chatId) { _, chat ->
                    chat.copy(unreadCount = update.unreadCount)
                } ?: run { fetchChat(update.chatId); null }
                needRefresh = true
            }
            is TdApi.UpdateChatTitle -> {
                chatsCache.computeIfPresent(update.chatId) { _, chat ->
                    chat.copy(title = update.title)
                }
                needRefresh = true
            }
            is TdApi.UpdateChatPhoto -> {
                chatsCache.computeIfPresent(update.chatId) { _, chat ->
                    chat.copy(photo = update.photo?.toDomain())
                }
                needRefresh = true
            }

            is TdApi.UpdateChatFolders -> {
                _mainChatListPosition.value = update.mainChatListPosition
                _chatFolders.update { currentFolders ->
                    val unreadCount = currentFolders.find { it.id == -1 }?.unreadCount ?: 0
                    val allChatsFolder = ChatFolderInfo(id = -1, unreadCount = unreadCount)

                    val customFolders = update.chatFolders
                        .map { it.toDomain() }
                        .filter { it.id != -1 }

                    customFolders.toMutableList().apply {
                        add(update.mainChatListPosition, allChatsFolder)
                    }.toList()
                }
            }

            is TdApi.UpdateUnreadChatCount -> {
                val targetFolderId = when (update.chatList) {
                    is TdApi.ChatListFolder -> (update.chatList as TdApi.ChatListFolder).chatFolderId
                    is TdApi.ChatListMain -> -1
                    else -> -2
                }
                _chatFolders.update { currentList ->
                    currentList.map { if (it.id == targetFolderId) it.copy(unreadCount = update.unreadCount) else it }
                }
            }

            is TdApi.UpdateUser -> {
                if (update.user.type !is TdApi.UserTypeBot) {
                    _userStatuses.update { map -> map + (update.user.id to update.user.status.toDomain()) }
                    chatsCache.computeIfPresent(update.user.id) { _, chat ->
                        chat.copy(status = update.user.status.toDomain())
                    }
                }
            }
            is TdApi.UpdateUserStatus -> {
                _userStatuses.update { map -> map + (update.userId to update.status.toDomain()) }
                chatsCache.computeIfPresent(update.userId) { _, chat ->
                    chat.copy(status = update.status.toDomain())
                }
            }
            else -> {}
        }

        if (needRefresh) {
            refreshTrigger.emit(Unit)
        }
    }

    private fun updateChatPositions(currentPositions: List<ChatPosition>, newPosition: TdApi.ChatPosition): List<ChatPosition> {
        val newPositions = currentPositions.toMutableList()
        val index = newPositions.indexOfFirst {
            isSameList(it.list, newPosition.list)
        }

        if (index != -1) {
            if (newPosition.order == 0L) {
                newPositions.removeAt(index)
            } else {
                newPositions[index] = newPosition.toDomain()
            }
        } else {
            if (newPosition.order != 0L) {
                newPositions.add(newPosition.toDomain())
            }
        }
        return newPositions
    }

    private fun isSameList(domainList: ChatList, tdList: TdApi.ChatList): Boolean {
        return when {
            domainList is ChatListMain && tdList.constructor == TdApi.ChatListMain.CONSTRUCTOR -> true
            domainList is ChatListFolder && tdList is TdApi.ChatListFolder -> domainList.chatFolderId == tdList.chatFolderId
            domainList is ChatListArchive && tdList.constructor == TdApi.ChatListArchive.CONSTRUCTOR -> true
            else -> false
        }
    }

    private fun fetchChat(chatId: Long) {
        clientManager.send(TdApi.GetChat(chatId)) { result ->
            if (result is TdApi.Chat) {
                repositoryScope.launch(Dispatchers.Default) {
                    chatsCache[result.id] = result.toDomain()
                    refreshTrigger.emit(Unit)
                }
            }
        }
    }

    override fun loadChats(folderId: Int, limit: Int) {
        val chatList = when(folderId) {
            -1 -> TdApi.ChatListMain()
            -2 -> TdApi.ChatListArchive()
            else -> TdApi.ChatListFolder(folderId)
        }
        clientManager.send(TdApi.LoadChats(chatList, limit))
    }

    override fun observeChatStatuses(): Flow<Map<Long, UserStatus>> = _userStatuses.asStateFlow()
    override fun observeMe(): Flow<User?> = _me.asStateFlow()
    override fun observeChatFolders(): Flow<List<ChatFolderInfo>> = _chatFolders.asStateFlow()
    override fun observeMainChatListPosition(): Flow<Int> = _mainChatListPosition.asStateFlow()
}