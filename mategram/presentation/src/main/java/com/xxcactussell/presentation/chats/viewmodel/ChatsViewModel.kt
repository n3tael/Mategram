package com.xxcactussell.presentation.chats.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xxcactussell.domain.chats.model.Chat
import com.xxcactussell.domain.chats.model.ChatList
import com.xxcactussell.domain.chats.model.ChatStatus
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
    val selectedFolderId : StateFlow<Int?> = _selectedFolderId.asStateFlow()
    val selectedChatId: MutableStateFlow<Long?> = MutableStateFlow(null)

    private val baseChatsFlow: StateFlow<Map<Int, List<ChatItemUiState>>> =
        combine(observeChats(), observeFolders()) { chatsMap, folders ->
            chatsMap.mapValues { (_, domainChats) ->
                domainChats.map { it.toBaseChatItemUiState() }
            }
        }
            .flowOn(Dispatchers.Default)
            .stateIn(viewModelScope, SharingStarted.Eagerly, emptyMap())

    val uiState: StateFlow<ChatListUiState> = combine(
        baseChatsFlow,
        observeFolders(),
        selectedFolderId,
        selectedChatId,
        observeMe()
    ) { baseChatsMap, folders, selectedFolder, selectedChat, me ->
        val updatedChatsMap = baseChatsMap.mapValues { (folderId, chats) ->
            chats.map { chatItem ->
                chatItem.copy(
                    isSelected = chatItem.chat.id == selectedChat,
                    isPinned = chatItem.chat.isPinnedInFolder(folderId, selectedFolder),
                )
            }
        }
        val allTabs = folders.map {
            TabInfoUi(it.id, it.name, it.unreadCount, it.id == selectedFolder)
        }
        val selectedIndex = allTabs.indexOfFirst { it.id == selectedFolder }

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

        ChatListUiState(
            sortedChats = updatedChatsMap,
            allTabs = allTabs,
            selectedIndex = selectedIndex,
            isLoading = baseChatsMap.isEmpty(),
            me = userUiState
        )
    }
        .flowOn(Dispatchers.Default)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ChatListUiState()
        )

    private fun Chat.isPinnedInFolder(chatFolderId: Int, selectedFolderId: Int?): Boolean {
        val position = this.positions.firstOrNull { pos ->
            val listId = when (val list = pos.list) {
                is ChatList.Main -> -1
                is ChatList.Folder -> list.id
                else -> null
            }
            listId == selectedFolderId
        }
        return position?.isPinned ?: false
    }

    private fun Chat.toBaseChatItemUiState(): ChatItemUiState {
        return ChatItemUiState(
            chat = this,
            isSelected = false,
            isUnread = this.unreadCount > 0,
            isPinned = false,
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
