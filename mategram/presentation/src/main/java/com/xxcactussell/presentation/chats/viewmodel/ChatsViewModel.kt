package com.xxcactussell.presentation.chats.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xxcactussell.domain.Chat
import com.xxcactussell.domain.ChatFolder
import com.xxcactussell.domain.ChatFolderInfo
import com.xxcactussell.domain.ChatList
import com.xxcactussell.domain.ChatListArchive
import com.xxcactussell.domain.ChatListFolder
import com.xxcactussell.domain.ChatListMain
import com.xxcactussell.domain.User
import com.xxcactussell.domain.UserStatusOnline
import com.xxcactussell.domain.toChatPhotoInfo
import com.xxcactussell.repositories.chats.model.ChatStatus
import com.xxcactussell.repositories.chats.repository.LoadChatsUseCase
import com.xxcactussell.repositories.chats.repository.ObserveChatFoldersUseCase
import com.xxcactussell.repositories.chats.repository.ObserveChatsUpdateUseCase
import com.xxcactussell.repositories.chats.repository.ObserveMeUseCase
import com.xxcactussell.presentation.chats.model.AvatarUiState
import com.xxcactussell.presentation.chats.model.ChatItemUiState
import com.xxcactussell.presentation.chats.model.ChatListUiState
import com.xxcactussell.presentation.chats.model.ChatsEvent
import com.xxcactussell.presentation.chats.model.TabInfoUi
import com.xxcactussell.presentation.chats.model.UserUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatsViewModel @Inject constructor(
    observeChats: ObserveChatsUpdateUseCase,
    observeFolders: ObserveChatFoldersUseCase,
    observeMe: ObserveMeUseCase,
    private val loadChatsUseCase: LoadChatsUseCase
) : ViewModel() {

    private val _selectedFolderId = MutableStateFlow<Int?>(null)
    val selectedFolderId: StateFlow<Int?> = _selectedFolderId.asStateFlow()
    val selectedChatId: MutableStateFlow<Long?> = MutableStateFlow(null)
    private val mappedChatsFlow: StateFlow<Map<Int, List<ChatItemUiState>>> =
        combine(observeChats(), observeFolders()) { allChats, folders ->
            processChatsIntoFolders(allChats, folders)
        }
            .flowOn(Dispatchers.Default)
            .stateIn(viewModelScope, SharingStarted.Eagerly, emptyMap())

    val uiState: StateFlow<ChatListUiState> = combine(
        mappedChatsFlow,
        observeFolders(),
        selectedFolderId,
        selectedChatId,
        observeMe()
    ) { chatsMap, folders, selectedFolder, selectedChat, me ->
        createChatListUiState(chatsMap, folders, selectedFolder, selectedChat, me)
    }
        .flowOn(Dispatchers.Default)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ChatListUiState()
        )

    private fun processChatsIntoFolders(
        allChats: List<Chat>,
        folders: List<ChatFolderInfo>
    ): Map<Int, List<ChatItemUiState>> {
        val folderMap = mutableMapOf<Int, ArrayList<Pair<Chat, Long>>>()
        folders.forEach { folderMap[it.id] = ArrayList() }
        folderMap.putIfAbsent(-1, ArrayList())
        folderMap.putIfAbsent(-2, ArrayList())

        allChats.forEach { chat ->
            chat.positions.forEach { pos ->
                if (pos.order == 0L) return@forEach

                val folderId = when (val list = pos.list) {
                    is ChatListMain -> -1
                    is ChatListArchive -> -2
                    is ChatListFolder -> list.chatFolderId
                }
                val list = folderMap.getOrPut(folderId) { ArrayList() }
                list.add(chat to pos.order)
            }
        }

        return folderMap.mapValues { (folderId, pairs) ->
            pairs.sortedWith(
                compareByDescending<Pair<Chat, Long>> { (chat, _) ->
                    chat.isPinnedInFolder(folderId)
                }.thenByDescending { (_, order) ->
                    order
                }
            ).map { (chat, _) ->
                chat.toBaseChatItemUiState(folderId)
            }
        }
    }

    private fun Chat.isPinnedInFolder(targetFolderId: Int): Boolean {
        return positions.any { pos ->
            val id = when (val list = pos.list) {
                is ChatListMain -> -1
                is ChatListArchive -> -2
                is ChatListFolder -> list.chatFolderId
            }
            id == targetFolderId && pos.isPinned
        }
    }

    private fun ChatList.isFolderId(id: Int): Boolean {
        return when(this) {
            is ChatListMain -> id == -1
            is ChatListArchive -> id == -2
            is ChatListFolder -> this.chatFolderId == id
        }
    }

    private fun createChatListUiState(
        baseChatsMap: Map<Int, List<ChatItemUiState>>,
        folders: List<ChatFolderInfo>,
        selectedFolder: Int?,
        selectedChat: Long?,
        me: User?
    ): ChatListUiState {
        val finalSelectedFolder = selectedFolder ?: -1

        val currentList = baseChatsMap[finalSelectedFolder] ?: emptyList()

        val updatedList = currentList.map { item ->
            if (item.chat.id == selectedChat) item.copy(isSelected = true) else item
        }

        val updatedMap = baseChatsMap.toMutableMap()
        updatedMap[finalSelectedFolder] = updatedList

        val allTabs = folders.map {
            TabInfoUi(it.id, it.name, it.unreadCount, it.id == finalSelectedFolder)
        }
        val selectedIndex = allTabs.indexOfFirst { it.id == finalSelectedFolder }.takeIf { it != -1 } ?: 0

        val userUiState = if (me != null) {
            UserUiState(
                user = me,
                avatar = AvatarUiState(
                    chatId = me.id,
                    photo = me.profilePhoto?.toChatPhotoInfo(),
                    title = me.firstName
                )
            )
        } else null

        return ChatListUiState(
            sortedChats = updatedMap,
            allTabs = allTabs,
            selectedIndex = selectedIndex,
            selectedFolderId = finalSelectedFolder,
            isLoading = baseChatsMap.isEmpty() && folders.isEmpty(),
            me = userUiState
        )
    }

    private fun Chat.toBaseChatItemUiState(folderId: Int = -1): ChatItemUiState {
        val position = this.positions.firstOrNull { pos -> pos.list.isFolderId(folderId) }

        return ChatItemUiState(
            chat = this,
            isSelected = false,
            isUnread = this.unreadCount > 0,
            isPinned = position?.isPinned ?: false,
            isOnline = this.status is UserStatusOnline,
            photo = AvatarUiState(
                chatId = this.id,
                photo = this.photo,
                title = this.title
            ),
        )
    }

    fun onEvent(event: ChatsEvent) {
        when (event) {
            is ChatsEvent.OnChatSwipedLeft -> {

            }
            is ChatsEvent.OnChatSwipedRight -> {

            }
            is ChatsEvent.OnFolderChanged -> {
                onFolderSelected(event.folderId)
            }
        }
    }

    fun loadInitialChats() {
        viewModelScope.launch {
            loadChatsUseCase(folderId = 0, limit = 100)
        }
    }

    fun onFolderSelected(folderId: Int) {
        _selectedFolderId.value = folderId
        viewModelScope.launch {
            loadChatsUseCase(folderId = folderId, limit = 100)
        }
    }
}
