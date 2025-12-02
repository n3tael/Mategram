package com.xxcactussell.data.impl

import com.xxcactussell.data.TdClientManager
import com.xxcactussell.data.utils.toChatFolder
import com.xxcactussell.data.utils.toDomain
import com.xxcactussell.data.utils.todomain.toDomain
import com.xxcactussell.domain.chats.model.Chat
import com.xxcactussell.domain.chats.model.ChatFolder
import com.xxcactussell.domain.chats.model.ChatList
import com.xxcactussell.domain.chats.model.ChatPosition
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

    private val chatLocationCache = ConcurrentHashMap<Long, MutableSet<Int>>()

    private val pendingChatUpdates = ConcurrentHashMap.newKeySet<Long>()

    private val _sortedChatsFlow = MutableStateFlow<Map<Int, List<Chat>>>(emptyMap())
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
                val chatId = when (update) {
                    is TdApi.UpdateNewChat -> update.chat.id
                    is TdApi.UpdateChatPosition -> update.chatId
                    is TdApi.UpdateChatLastMessage -> update.chatId
                    is TdApi.UpdateChatReadInbox -> update.chatId
                    is TdApi.UpdateChatTitle -> update.chatId
                    is TdApi.UpdateChatPhoto -> update.chatId
                    is TdApi.UpdateUser -> update.user.id
                    is TdApi.UpdateUserStatus -> update.userId
                    is TdApi.UpdateChatReadOutbox -> update.chatId
                    else -> null
                }
                handleChatUpdate(update)
                chatId?.let { pendingChatUpdates.add(it) }
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
                delay(250)

                if (pendingChatUpdates.isNotEmpty()) {
                    val idsToUpdate = pendingChatUpdates.toList()
                    pendingChatUpdates.clear()

                    if (idsToUpdate.isNotEmpty()) {
                        updateSortedChatsBatch(idsToUpdate)
                    }
                }
            }
        }
    }

    private fun updateSortedChatsBatch(chatIds: List<Long>) {
        _sortedChatsFlow.update { currentMap ->
            val newMap = currentMap.toMutableMap()

            chatIds.forEach { chatId ->
                val updatedChat = chatsCache[chatId] ?: return@forEach

                val oldFolderIds = chatLocationCache[chatId] ?: emptySet()
                oldFolderIds.forEach { folderId ->
                    newMap[folderId]?.let { list ->
                        val mutableList = list as? MutableList ?: list.toMutableList()
                        mutableList.removeIf { it.id == chatId }
                        newMap[folderId] = mutableList
                    }
                }

                val newLocations = mutableSetOf<Int>()

                updatedChat.positions
                    .filter { it.order != 0L }
                    .forEach { position ->
                        val folderId = getFolderIdFromChatList(position.list)
                        val currentList = newMap.getOrDefault(folderId, emptyList())
                        val mutableList = currentList as? MutableList ?: currentList.toMutableList()

                        val comparator = getChatComparator(folderId)
                        val insertionPoint = mutableList.binarySearch(updatedChat, comparator)
                        val index = if (insertionPoint < 0) -(insertionPoint + 1) else insertionPoint

                        if (index in 0..mutableList.size) {
                            mutableList.add(index, updatedChat)
                        } else {
                            mutableList.add(updatedChat)
                        }

                        newMap[folderId] = mutableList
                        newLocations.add(folderId)
                    }
                chatLocationCache[chatId] = newLocations
            }
            newMap
        }
    }

    private fun getChatComparator(folderId: Int): Comparator<Chat> {
        return compareByDescending<Chat> { it.findPositionForFolder(folderId)?.isPinned ?: false }
            .thenByDescending { it.findPositionForFolder(folderId)?.order ?: 0L }
            .thenByDescending { it.id }
    }

    private fun Chat.findPositionForFolder(folderId: Int): ChatPosition? {
        return this.positions.find { pos -> getFolderIdFromChatList(pos.list) == folderId }
    }

    private fun getFolderIdFromChatList(list: ChatList): Int {
        return when (list) {
            is ChatList.Main -> -1
            is ChatList.Archive -> -2
            is ChatList.Folder -> list.id
        }
    }

    private fun handleChatUpdate(update: TdApi.Object) {
        when (update) {
            is TdApi.UpdateChatReadOutbox -> {
                val chat = chatsCache[update.chatId]
                if (chat != null) {
                    val updatedChat = chat.copy(lastReadOutboxMessageId = update.lastReadOutboxMessageId)
                    chatsCache[update.chatId] = updatedChat
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
                            else -> false
                        }
                    }
                    if (index != -1) {
                        newPositions[index] = update.position.toDomain()
                    } else {
                        newPositions.add(update.position.toDomain())
                    }
                    val updatedChat = chat.copy(positions = newPositions)
                    chatsCache[chat.id] = updatedChat
                } else {
                    clientManager.send(TdApi.GetChat(update.chatId)) { result ->
                        if (result is TdApi.Chat) {
                            chatsCache[result.id] = result.toDomain()
                            pendingChatUpdates.add(result.id)
                        }
                    }
                }
            }
            is TdApi.UpdateChatLastMessage -> {
                val chat = chatsCache[update.chatId]
                if (chat != null) {
                    val updatedChat = chat.copy(lastMessage = update.lastMessage?.toDomain())
                    chatsCache[chat.id] = updatedChat
                } else {
                    clientManager.send(TdApi.GetChat(update.chatId)) { result ->
                        if (result is TdApi.Chat) {
                            chatsCache[result.id] = result.toDomain()
                            pendingChatUpdates.add(result.id)
                        }
                    }
                }
            }
            is TdApi.UpdateChatReadInbox -> {
                val chat = chatsCache[update.chatId]
                if (chat != null) {
                    val updatedChat = chat.copy(unreadCount = update.unreadCount)
                    chatsCache[chat.id] = updatedChat
                } else {
                    clientManager.send(TdApi.GetChat(update.chatId)) { result ->
                        if (result is TdApi.Chat) {
                            chatsCache[result.id] = result.toDomain()
                            pendingChatUpdates.add(result.id)
                        }
                    }
                }
            }
            is TdApi.UpdateChatTitle -> {
                val chat = chatsCache[update.chatId]
                if (chat != null) {
                    val updatedChat = chat.copy(title = update.title)
                    chatsCache[chat.id] = updatedChat
                } else {
                    clientManager.send(TdApi.GetChat(update.chatId)) { result ->
                        if (result is TdApi.Chat) {
                            chatsCache[result.id] = result.toDomain()
                            pendingChatUpdates.add(result.id)
                        }
                    }
                }
            }
            is TdApi.UpdateChatPhoto -> {
                val chat = chatsCache[update.chatId]
                if (chat != null) {
                    val updatedChat = chat.copy(photo = update.photo?.toDomain())
                    chatsCache[chat.id] = updatedChat
                } else {
                    clientManager.send(TdApi.GetChat(update.chatId)) { result ->
                        if (result is TdApi.Chat) {
                            chatsCache[result.id] = result.toDomain()
                            pendingChatUpdates.add(result.id)
                        }
                    }
                }
            }
            is TdApi.UpdateUser -> {
                if (update.user.type !is TdApi.UserTypeBot) {
                    _userStatuses.update { map ->
                        map.toMutableMap().apply {
                            this[update.user.id] = update.user.status.toDomain()
                        }
                    }
                    val updatedChat = chatsCache[update.user.id]?.copy(status = update.user.status.toDomain())
                    if (updatedChat != null) {
                        chatsCache[update.user.id] = updatedChat
                    }
                }
            }
            is TdApi.UpdateUserStatus -> {
                _userStatuses.update { map ->
                    map.toMutableMap().apply {
                        this[update.userId] = update.status.toDomain()
                    }
                }
                val updatedChat = chatsCache[update.userId]?.copy(status = update.status.toDomain())
                if (updatedChat != null) {
                    chatsCache[update.userId] = updatedChat
                }
            }
            is TdApi.UpdateUnreadChatCount -> {
                val targetFolderId = when (update.chatList) {
                    is TdApi.ChatListFolder -> (update.chatList as TdApi.ChatListFolder).chatFolderId
                    is TdApi.ChatListMain -> -1
                    else -> -2
                }
                _chatFolders.update { currentList ->
                    currentList.map { chatFolder ->
                        if (chatFolder.id == targetFolderId) {
                            chatFolder.copy(unreadCount = update.unreadCount)
                        } else {
                            chatFolder
                        }
                    }
                }
            }
        }
    }

    override fun observeSortedChats(): Flow<Map<Int, List<Chat>>> = _sortedChatsFlow.asStateFlow()

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