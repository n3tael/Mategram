package com.xxcactussell.data.impl

import com.xxcactussell.data.TdClientManager
import com.xxcactussell.data.utils.toChatFolder
import com.xxcactussell.data.utils.toDomain
import com.xxcactussell.data.utils.todomain.toDomain
import com.xxcactussell.domain.chats.model.Chat
import com.xxcactussell.domain.chats.model.ChatFolder
import com.xxcactussell.domain.chats.model.ChatList
import com.xxcactussell.domain.chats.model.ChatStatus
import com.xxcactussell.domain.chats.model.User
import com.xxcactussell.domain.chats.repository.ChatsRepository
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.drinkless.tdlib.TdApi
import java.util.concurrent.ConcurrentHashMap

class ChatsRepositoryImpl @Inject constructor(
    private val clientManager: TdClientManager
) : ChatsRepository {
    private val chatsCache = ConcurrentHashMap<Long, Chat>()
    private val pendingUpdates = MutableStateFlow(false)
    private val _chatsFlow = MutableStateFlow<List<Chat>>(emptyList())

    private val _chatFolders = MutableStateFlow<List<ChatFolder>>(emptyList())
    private val _mainChatListPosition = MutableStateFlow(0)
    private val _userStatuses = MutableStateFlow<Map<Long, ChatStatus>>(emptyMap())

    private val repositoryScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private val _me = MutableStateFlow<User?>(null)

    init {
        clientManager.send(TdApi.GetMe()) { result ->
            if (result is TdApi.User) {
                _me.value = result.toDomain()
            }
        }

        clientManager.chatsUpdatesFlow
            .onEach { update ->
                handleChatUpdate(update)
                pendingUpdates.value = true
            }
            .launchIn(repositoryScope)

        clientManager.chatFoldersUpdatesFlow
            .onEach { update ->
                handleChatUpdate(update)
            }
            .launchIn(repositoryScope)

        startBatchProcessing()
    }

    private fun startBatchProcessing() {
        repositoryScope.launch(Dispatchers.Default) {
            while (true) {
                if (pendingUpdates.value) {
                    delay(200)
                    pendingUpdates.value = false
                    emitChats()
                } else {
                    delay(50)
                }
            }
        }
    }

    private fun emitChats() {
        val allChats = chatsCache.values.toList()
        _chatsFlow.value = allChats
    }

    override fun observeChats(): Flow<List<Chat>> = _chatsFlow.asStateFlow()

    private fun handleChatUpdate(update: TdApi.Object) {
        when (update) {
            is TdApi.UpdateNewChat -> {
                chatsCache[update.chat.id] = update.chat.toDomain()
            }
            is TdApi.UpdateChatPosition -> {
                val chat = chatsCache[update.chatId]
                if (chat != null) {
                    val newPositions = chat.positions.toMutableList()
                    val index = newPositions.indexOfFirst {
                        when {
                            it.list is ChatList.Main && update.position.list.constructor == TdApi.ChatListMain.CONSTRUCTOR -> true
                            it.list is ChatList.Folder && update.position.list is TdApi.ChatListFolder ->
                                (it.list as ChatList.Folder).id == (update.position.list as TdApi.ChatListFolder).chatFolderId
                            it.list is ChatList.Archive && update.position.list.constructor == TdApi.ChatListArchive.CONSTRUCTOR -> true
                            else -> false
                        }
                    }
                    if (index != -1) {
                        if (update.position.order == 0L) {
                            newPositions.removeAt(index)
                        } else {
                            newPositions[index] = update.position.toDomain()
                        }
                    } else {
                        if (update.position.order != 0L) {
                            newPositions.add(update.position.toDomain())
                        }
                    }
                    val updatedChat = chat.copy(positions = newPositions)
                    chatsCache[chat.id] = updatedChat
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
            }
            is TdApi.UpdateChatReadInbox -> {
                chatsCache.computeIfPresent(update.chatId) { _, chat ->
                    chat.copy(unreadCount = update.unreadCount)
                } ?: run { fetchChat(update.chatId); null }
            }
            is TdApi.UpdateChatTitle -> {
                chatsCache.computeIfPresent(update.chatId) { _, chat ->
                    chat.copy(title = update.title)
                }
            }
            is TdApi.UpdateChatPhoto -> {
                chatsCache.computeIfPresent(update.chatId) { _, chat ->
                    chat.copy(photo = update.photo?.toDomain())
                }
            }

            is TdApi.UpdateChatFolders -> {
                val mainChatListPosition = update.mainChatListPosition
                val currentAllChatsFolder = _chatFolders.value.find { it.id == -1 }
                val unreadCountToPreserve = currentAllChatsFolder?.unreadCount ?: 0
                val customFolders = update.chatFolders
                    .map { it.toChatFolder() }
                    .filter { it.id != -1 }
                val newAllChatsFolder = ChatFolder(
                    id = -1,
                    unreadCount = unreadCountToPreserve
                )
                val finalFolders = customFolders.toMutableList().apply {
                    add(mainChatListPosition, newAllChatsFolder)
                }.toList()
                _chatFolders.value = finalFolders
                _mainChatListPosition.value = mainChatListPosition
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
    }

    private fun fetchChat(chatId: Long) {
        clientManager.send(TdApi.GetChat(chatId)) { result ->
            if (result is TdApi.Chat) {
                chatsCache[result.id] = result.toDomain()
                pendingUpdates.value = true
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

    override fun observeChatStatuses(): Flow<Map<Long, ChatStatus>> = _userStatuses.asStateFlow()
    override fun observeMe(): Flow<User?> = _me.asStateFlow()
    override fun observeChatFolders(): Flow<List<ChatFolder>> = _chatFolders.asStateFlow()
    override fun observeMainChatListPosition(): Flow<Int> = _mainChatListPosition.asStateFlow()
}