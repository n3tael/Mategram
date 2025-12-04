package com.xxcactussell.presentation.chats.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xxcactussell.domain.chats.model.Chat
import com.xxcactussell.domain.chats.model.ChatFolder
import com.xxcactussell.domain.chats.model.ChatList
import com.xxcactussell.domain.chats.model.ChatStatus
import com.xxcactussell.domain.chats.model.User
import com.xxcactussell.domain.chats.model.toChatPhoto
import com.xxcactussell.domain.chats.repository.LoadChatsUseCase
import com.xxcactussell.domain.chats.repository.ObserveChatFoldersUseCase
import com.xxcactussell.domain.chats.repository.ObserveChatsUpdateUseCase
import com.xxcactussell.domain.chats.repository.ObserveMeUseCase
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
        folders: List<ChatFolder>
    ): Map<Int, List<ChatItemUiState>> {
        val folderMap = mutableMapOf<Int, ArrayList<Pair<Chat, Long>>>()
        folders.forEach { folderMap[it.id] = ArrayList() }
        folderMap.putIfAbsent(-1, ArrayList())
        folderMap.putIfAbsent(-2, ArrayList())

        allChats.forEach { chat ->
            chat.positions.forEach { pos ->
                if (pos.order == 0L) return@forEach

                val folderId = when (val list = pos.list) {
                    is ChatList.Main -> -1
                    is ChatList.Archive -> -2
                    is ChatList.Folder -> list.id
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
                is ChatList.Main -> -1
                is ChatList.Archive -> -2
                is ChatList.Folder -> list.id
            }
            id == targetFolderId && pos.isPinned
        }
    }

    private fun ChatList.isFolderId(id: Int): Boolean {
        return when(this) {
            is ChatList.Main -> id == -1
            is ChatList.Archive -> id == -2
            is ChatList.Folder -> this.id == id
        }
    }

    private fun createChatListUiState(
        baseChatsMap: Map<Int, List<ChatItemUiState>>,
        folders: List<ChatFolder>,
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
                    photo = me.profilePhoto?.toChatPhoto(),
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
            isOnline = this.status is ChatStatus.Online,
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
