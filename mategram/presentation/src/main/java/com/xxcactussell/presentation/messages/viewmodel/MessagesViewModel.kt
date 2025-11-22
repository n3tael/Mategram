package com.xxcactussell.presentation.messages.viewmodel

import android.net.Uri
import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.InsertDriveFile
import androidx.compose.material.icons.outlined.Checklist
import androidx.compose.material.icons.outlined.InsertPhoto
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.MusicNote
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Poll
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xxcactussell.domain.chats.model.Chat
import com.xxcactussell.domain.chats.model.ChatMemberStatus
import com.xxcactussell.domain.chats.model.ChatStatus
import com.xxcactussell.domain.chats.model.ChatType
import com.xxcactussell.domain.chats.model.User
import com.xxcactussell.domain.chats.model.toChatPhoto
import com.xxcactussell.domain.chats.model.toStringKey
import com.xxcactussell.domain.chats.repository.ObserveChatStatusesUseCase
import com.xxcactussell.domain.messages.model.InputMessageContent
import com.xxcactussell.domain.messages.model.Message
import com.xxcactussell.domain.messages.model.MessageSource
import com.xxcactussell.domain.messages.model.getId
import com.xxcactussell.domain.messages.repository.BuildMessageContentUseCase
import com.xxcactussell.domain.messages.repository.CloseChatUseCase
import com.xxcactussell.domain.messages.repository.GetMessagesWithAlbumBoundaryUseCase
import com.xxcactussell.domain.messages.repository.GetSendersInfoUseCase
import com.xxcactussell.domain.messages.repository.MarkMessageAsRead
import com.xxcactussell.domain.messages.repository.ObserveLastReadOutboxMessageUseCase
import com.xxcactussell.domain.messages.repository.ObserveNewMessagesUseCase
import com.xxcactussell.domain.messages.repository.ObserveSucceededMessagesUseCase
import com.xxcactussell.domain.messages.repository.OpenChatUseCase
import com.xxcactussell.domain.messages.repository.SendMessageUseCase
import com.xxcactussell.presentation.chats.model.AttachmentEntry
import com.xxcactussell.presentation.chats.model.AvatarUiState
import com.xxcactussell.presentation.chats.model.ChatEffect
import com.xxcactussell.presentation.localization.localizedString
import com.xxcactussell.presentation.messages.model.AttachmentEvent
import com.xxcactussell.presentation.messages.model.InputEvent
import com.xxcactussell.presentation.messages.model.MessageUiItem
import com.xxcactussell.presentation.messages.model.MessagesEvent
import com.xxcactussell.presentation.messages.model.MessagesEvent.*
import com.xxcactussell.presentation.messages.model.MessagesUiState
import com.xxcactussell.presentation.messages.model.RecordingMode
import com.xxcactussell.presentation.messages.model.getAlbumId
import com.xxcactussell.presentation.messages.model.getMessageDate
import com.xxcactussell.presentation.messages.model.getMessageId
import com.xxcactussell.presentation.tools.isSameDay
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import okhttp3.internal.cacheGet


@AssistedFactory
interface MessagesViewModelFactory {
    fun create(chatId: Long): MessagesViewModel
}

@HiltViewModel(assistedFactory = MessagesViewModelFactory::class)
class MessagesViewModel @AssistedInject constructor(
    private val openChatUseCase: OpenChatUseCase,
    private val closeChatUseCase: CloseChatUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val observeChatStatuses: ObserveChatStatusesUseCase,
    private val buildMessageContent: BuildMessageContentUseCase,
    private val getSendersInfoUseCase: GetSendersInfoUseCase,
    observeNewMessagesUseCase: ObserveNewMessagesUseCase,
    observeSucceedMessagesUseCase: ObserveSucceededMessagesUseCase,
    observeLastReadOutboxMessageUseCase: ObserveLastReadOutboxMessageUseCase,
    private val markMessageAsRead: MarkMessageAsRead,
    private val getMessagesWithAlbumBoundaryUseCase: GetMessagesWithAlbumBoundaryUseCase,
    @Assisted private val chatId: Long
) : ViewModel() {
    private val _uiState = MutableStateFlow(MessagesUiState())
    val uiState: StateFlow<MessagesUiState> = _uiState.asStateFlow()

    private val albumMessageBuffer = mutableMapOf<Long, MutableList<Message>>()
    private val albumProcessingJobs = mutableMapOf<Long, Job>()
    private val ALBUM_GROUPING_DELAY_MS = 500L
    private val albumBufferMutex = Mutex()
    private var lastKnownFirstVisibleItemIndex: Int = 0

    private val _effects = Channel<ChatEffect>()
    val effects = _effects.receiveAsFlow()


    init {
        viewModelScope.launch {
            try {
                val chat = openChatUseCase(chatId)
                Log.d("MessagesViewModel", "Chat loaded: ${chat?.id}")
                if (chat != null) {
                    val attachmentEntries = createAttachmentEntries(chat)
                    _uiState.update {
                        it.copy(
                            chat = chat,
                            attachmentEntries = attachmentEntries,
                        )
                    }
                    loadHistory(isInitialLoad = true)

                    when (chat.type) {
                        is ChatType.Secret,
                        is ChatType.Private -> {
                            observeStatus()
                        }
                        is ChatType.BasicGroup -> {
                            _uiState.update {
                                it.copy(
                                    chatStatusStringKey = "Members"
                                )
                            }
                        }
                        is ChatType.Supergroup -> {
                            _uiState.update {
                                it.copy(
                                    chatStatusStringKey = if ((chat.type as ChatType.Supergroup).isChannel) "Subscribers" else "Members"
                                )
                            }
                        }
                    }

                } else {
                    _uiState.update { it.copy(error = "Чат не найден",) }
                }

            } catch (e: Exception) {
                _uiState.update { it.copy(error = "Ошибка загрузки чата: ${e.message}",) }
            }
        }
        observeNewMessagesUseCase()
            .onEach { newMessage ->
                if (newMessage.chatId == chatId) {
                    if (newMessage.mediaAlbumId != 0L) {
                        albumBufferMutex.withLock {
                            albumMessageBuffer.getOrPut(newMessage.mediaAlbumId) { mutableListOf() }.add(newMessage)
                        }

                        albumProcessingJobs[newMessage.mediaAlbumId]?.cancel()

                        albumProcessingJobs[newMessage.mediaAlbumId] = viewModelScope.launch {
                            delay(ALBUM_GROUPING_DELAY_MS)
                            processBufferedAlbum(newMessage.mediaAlbumId)
                        }
                    } else {
                        processNewMessages(listOf(newMessage))
                    }
                }
            }
            .launchIn(viewModelScope)

        observeSucceedMessagesUseCase()
            .onEach { (oldId, updatedMessage) ->
                processSucceededMessage(oldId, updatedMessage)
            }
            .launchIn(viewModelScope)

        observeLastReadOutboxMessageUseCase()
            .onEach { (chatId, messageId) ->
                if (chatId == _uiState.value.chat?.id) {
                    _uiState.update {
                        it.copy(
                            chat = it.chat?.copy(lastReadOutboxMessageId = messageId)
                        )
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    private fun observeStatus() {
        observeChatStatuses()
            .onEach { status ->
                if (status[_uiState.value.chat?.id ?: 0L] != null) {
                    _uiState.update {
                        val wasOnline = if (status[it.chat?.id ?: 0L]!! is ChatStatus.Offline) (status[it.chat?.id ?: 0L] as ChatStatus.Offline).wasOnline else 0
                        it.copy(
                            chatStatusStringKey = status[it.chat?.id ?: 0L]!!.toStringKey(),
                            wasOnline = wasOnline
                        )
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    private suspend fun processBufferedAlbum(albumId: Long) {
        val bufferedMessages = albumBufferMutex.withLock {
            albumProcessingJobs.remove(albumId)
            albumMessageBuffer.remove(albumId)
        }

        if (!bufferedMessages.isNullOrEmpty()) {
            processNewMessages(bufferedMessages.sortedBy { it.date })
        }
    }

    private fun processSucceededMessage(oldId: Long, updatedMessage: Message) {
        _uiState.update { currentState ->
            val updatedMessages = currentState.messages.map { uiItem ->
                when (uiItem) {
                    is MessageUiItem.MessageItem -> {
                        if (uiItem.message.id == oldId) {
                            uiItem.copy(message = updatedMessage)
                        } else {
                            uiItem
                        }
                    }
                    is MessageUiItem.AlbumItem -> {
                        val messageIndex = uiItem.messages.indexOfFirst { it.message.id == oldId }

                        if (messageIndex != -1) {
                            val newAlbumMessages = uiItem.messages.toMutableList()
                            val oldMessageItem = newAlbumMessages[messageIndex]
                            newAlbumMessages[messageIndex] = oldMessageItem.copy(message = updatedMessage)
                            uiItem.copy(messages = newAlbumMessages)
                        } else {
                            uiItem
                        }
                    }
                    is MessageUiItem.DateSeparator -> {
                        uiItem
                    }
                }
            }
            currentState.copy(messages = updatedMessages)
        }
    }

    private fun processNewMessages(newRawMessages: List<Message>) {
        viewModelScope.launch {
            val uniqueSenderIds = newRawMessages
                .map { it.senderId }
                .toSet()

            val sendersMap = getSendersInfoUseCase(uniqueSenderIds)

            val newUiItems = withContext(Dispatchers.Default) {
                mapAndGroupMessagesToUi(newRawMessages, sendersMap)
            }

            val newItemsMap = withContext(Dispatchers.Default) {
                newUiItems
                    .mapNotNull { uiItem -> uiItem.getMessageId()?.let { id -> id to uiItem } }
                    .toMap()
            }

            _uiState.update { currentState ->
                val existingIdMap = currentState.messages
                    .asSequence()
                    .mapNotNull { uiItem -> uiItem.getMessageId()?.let { id -> id to uiItem } }
                    .toMap()

                val updatedMessages = currentState.messages.map { existingItem ->
                    val id = existingItem.getMessageId()
                    newItemsMap[id] ?: existingItem
                }

                val trulyNewItems = newUiItems.filter { newItem ->
                    val id = newItem.getMessageId()
                    id != null && !existingIdMap.containsKey(id)
                }

                val newestNewMessageDate = trulyNewItems.firstOrNull()?.getMessageDate()
                val oldestExistingMessageDate = currentState.messages.firstOrNull()?.getMessageDate()

                val shouldInsertDateSeparator = newestNewMessageDate != null &&
                        oldestExistingMessageDate != null &&
                        !isSameDay(newestNewMessageDate, oldestExistingMessageDate)
                val finalMessages = mutableListOf<MessageUiItem>()
                finalMessages.addAll(trulyNewItems)
                if (shouldInsertDateSeparator) {
                    finalMessages.add(MessageUiItem.DateSeparator(oldestExistingMessageDate))
                }
                finalMessages.addAll(updatedMessages)

                currentState.copy(
                    messages = finalMessages.toList(),
                )
            }
        }
    }

    private fun createAttachmentEntries(chat: Chat): List<AttachmentEntry> {
        val attachmentEntries = mutableListOf<AttachmentEntry>()
        val recordingMode = mutableListOf<RecordingMode>()

        if (chat.permissions.canSendPhotos || chat.permissions.canSendVideos
            || chat.myMemberStatus is ChatMemberStatus.Creator || (chat.myMemberStatus is ChatMemberStatus.Administrator && (chat.myMemberStatus as ChatMemberStatus.Administrator).rights.canPostMessages)
        ) attachmentEntries.add(
            AttachmentEntry(
                icon = Icons.Outlined.InsertPhoto,
                label = "ChatGallery",
                event = AttachmentEvent.ChatGallery
            )
        )

        if (chat.permissions.canSendDocuments
            || chat.myMemberStatus is ChatMemberStatus.Creator || (chat.myMemberStatus is ChatMemberStatus.Administrator && (chat.myMemberStatus as ChatMemberStatus.Administrator).rights.canPostMessages)
        ) attachmentEntries.add(
            AttachmentEntry(
                icon = Icons.AutoMirrored.Outlined.InsertDriveFile,
                label = "ChatDocument",
                event = AttachmentEvent.ChatDocument
            )
        )
        if (chat.permissions.canSendPolls
            || chat.myMemberStatus is ChatMemberStatus.Creator || (chat.myMemberStatus is ChatMemberStatus.Administrator && (chat.myMemberStatus as ChatMemberStatus.Administrator).rights.canPostMessages)
        ) {
            attachmentEntries.add(
                AttachmentEntry(
                    icon = Icons.Outlined.Poll,
                    label = "Poll",
                    event = AttachmentEvent.Poll
                )
            )
            attachmentEntries.add(
                AttachmentEntry(
                    icon = Icons.Outlined.Checklist,
                    label = "Todo",
                    event = AttachmentEvent.Todo
                )
            )
        }
        if (chat.permissions.canSendAudios
            || chat.myMemberStatus is ChatMemberStatus.Creator || (chat.myMemberStatus is ChatMemberStatus.Administrator && (chat.myMemberStatus as ChatMemberStatus.Administrator).rights.canPostMessages)
        ) {
            attachmentEntries.add(
                AttachmentEntry(
                    icon = Icons.Outlined.MusicNote,
                    label = "AttachMusic",
                    event = AttachmentEvent.AttachMusic
                )
            )
        }
        if (chat.permissions.canSendBasicMessages
            || chat.myMemberStatus is ChatMemberStatus.Creator || (chat.myMemberStatus is ChatMemberStatus.Administrator && (chat.myMemberStatus as ChatMemberStatus.Administrator).rights.canPostMessages)
        ) {
            attachmentEntries.add(
                AttachmentEntry(
                    icon = Icons.Outlined.Person,
                    label = "AttachContact",
                    event = AttachmentEvent.AttachContact
                )
            )
            attachmentEntries.add(
                AttachmentEntry(
                    icon = Icons.Outlined.LocationOn,
                    label = "ChatLocation",
                    event = AttachmentEvent.ChatLocation
                )
            )
        }
        if (chat.permissions.canSendVoiceNotes
            || chat.myMemberStatus is ChatMemberStatus.Creator || (chat.myMemberStatus is ChatMemberStatus.Administrator && (chat.myMemberStatus as ChatMemberStatus.Administrator).rights.canPostMessages)
        ) _uiState.update { it.copy(recordingMode = RecordingMode.Audio,) }
        else if (chat.permissions.canSendVideoNotes) _uiState.update { it.copy(recordingMode = RecordingMode.Video,) }

        if (chat.permissions.canSendVoiceNotes
            || chat.myMemberStatus is ChatMemberStatus.Creator || (chat.myMemberStatus is ChatMemberStatus.Administrator && (chat.myMemberStatus as ChatMemberStatus.Administrator).rights.canPostMessages)
        ) recordingMode.add(RecordingMode.Audio)
        if (chat.permissions.canSendVideoNotes
            || chat.myMemberStatus is ChatMemberStatus.Creator || (chat.myMemberStatus is ChatMemberStatus.Administrator && (chat.myMemberStatus as ChatMemberStatus.Administrator).rights.canPostMessages)
        ) recordingMode.add(RecordingMode.Video)

        if (recordingMode.isNotEmpty()) _uiState.update { it.copy(availableRecordingMode = recordingMode,) }

        return attachmentEntries
    }

    override fun onCleared() {
        super.onCleared()
        closeChat(chatId)
        albumProcessingJobs.values.forEach { it.cancel() }
    }

    fun onEvent(event: Any) {
        when (event) {
            is MessagesEvent -> onMessagesEvent(event)
            is InputEvent -> onInputEvent(event)
            is AttachmentEvent -> onAttachmentEvent(event)
            else -> {}
        }
    }

    fun onMessagesEvent(event: MessagesEvent) {
        when (event) {
            is SendClicked -> sendMessage(event.content)
            is LoadMoreHistory -> loadHistory(isInitialLoad = false)
            is DismissError -> _uiState.update { it.copy() }
            is MessageClicked -> { /* TODO */ }
            is MessageLongClicked -> { /* TODO */ }
            is MessageSwiped -> { /* TODO */ }
            is ShowScrollToBottomButton -> {
                _uiState.update { it.copy(showScrollToBottomButton = true) }
            }
            is HideScrollToBottomButton -> {
                _uiState.update { it.copy(showScrollToBottomButton = false) }
            }
            is MessageRead -> {
                if (event.messageId != null && _uiState.value.chat?.id != null) {
                    markMessageAsRead( _uiState.value.chat!!.id, event.messageId, MessageSource.ChatHistory)
                    _uiState.update { currentState ->
                        val currentChat = currentState.chat
                        if (currentChat != null && event.messageId > currentChat.lastReadInboxMessageId) {
                            val updatedChat = currentChat.copy(lastReadInboxMessageId = event.messageId)
                            val unreadCount = calculateUnreadBelowCount(
                                firstVisibleItemIndex = lastKnownFirstVisibleItemIndex,
                                messages = currentState.messages,
                                lastReadInboxMessageId = event.messageId
                            )
                            currentState.copy(chat = updatedChat, unreadBelowCount = unreadCount,)
                        } else {
                            currentState
                        }
                    }
                }
            }
            is UpdateFirstVisibleItemIndex -> {
                lastKnownFirstVisibleItemIndex = event.index
                _uiState.update { currentState ->
                    val unreadCount = calculateUnreadBelowCount(
                        firstVisibleItemIndex = event.index,
                        messages = currentState.messages,
                        lastReadInboxMessageId = currentState.chat?.lastReadInboxMessageId
                    )
                    currentState.copy(unreadBelowCount = unreadCount,)
                }
            }
        }
    }

    fun onInputEvent(event: InputEvent) {
        when (event) {
            is InputEvent.SendClicked -> {
                viewModelScope.launch {
                    if (_uiState.value.inputMessage.isNotBlank() || _uiState.value.selectedMediaUris.isNotEmpty()) {
                        val currentState = _uiState.value
                        val text = currentState.inputMessage
                        val attachmentsType = currentState.attachmentsType
                        val contents: List<InputMessageContent> = buildMessageContent(currentState.selectedMediaUris, text, emptyList(), attachmentsType)

                        onMessagesEvent(SendClicked(contents))
                    }
                }
            }
            is InputEvent.SwitchRecordingMode -> {
                _uiState.update { currentState ->
                    val nextMode = when (currentState.recordingMode) {
                        RecordingMode.Text -> currentState.availableRecordingMode.firstOrNull() ?: RecordingMode.Text
                        RecordingMode.Audio -> if (currentState.availableRecordingMode.contains(RecordingMode.Video)) RecordingMode.Video else RecordingMode.Text
                        RecordingMode.Video -> if (currentState.availableRecordingMode.contains(RecordingMode.Audio)) RecordingMode.Audio else RecordingMode.Text
                    }
                    currentState.copy(recordingMode = nextMode,)
                }
            }
            is InputEvent.MediaDropped -> { /* TODO */ }
            is InputEvent.TextChanged -> {
                _uiState.update { it.copy(inputMessage = event.text,) }
            }
            is InputEvent.ClearAttachments -> {
                clearSelectedMediaUris(event.uri)
            }
            is InputEvent.TextDropped -> { /* TODO */ }
            is InputEvent.OpenAttachmentsMenu -> {
                _uiState.update {
                    it.copy(
                        showAttachmentsMenu = true
                    )
                }
            }
            is InputEvent.CloseAttachmentsMenu -> {
                _uiState.update {
                    it.copy(
                        showAttachmentsMenu = false
                    )
                }
            }

            is InputEvent.DocumentsSelected -> {
                _uiState.update { it.copy(selectedMediaUris = event.uris) }
            }

            is InputEvent.MediaSelected -> {
                _uiState.update { it.copy(selectedMediaUris = event.uris, attachmentsType = "Media") }
            }
        }
    }

    fun onAttachmentEvent(event: AttachmentEvent) {
        viewModelScope.launch {
            when (event) {
                is AttachmentEvent.ChatGallery -> {
                    _effects.send(ChatEffect.LaunchMediaPicker)
                    _uiState.update {
                        it.copy(
                            showAttachmentsMenu = false
                        )
                    }
                }

                is AttachmentEvent.ChatDocument -> {
                    _effects.send(ChatEffect.LaunchDocumentPicker)
                    _uiState.update {
                        it.copy(
                            showAttachmentsMenu = false
                        )
                    }
                }

                is AttachmentEvent.Poll -> { /* TODO */
                }

                is AttachmentEvent.Todo -> { /* TODO */
                }

                is AttachmentEvent.AttachMusic -> {
                    _effects.send(ChatEffect.LaunchMusicPicker)
                    _uiState.update {
                        it.copy(
                            showAttachmentsMenu = false
                        )
                    }
                }

                is AttachmentEvent.AttachContact -> { /* TODO */
                }

                is AttachmentEvent.ChatLocation -> { /* TODO */
                }
            }
        }
    }

    private fun loadHistory(isInitialLoad: Boolean) {
        Log.d("MessagesViewModel", "isInitialLoad: $isInitialLoad")
        if (_uiState.value.isLoadingHistory || (!isInitialLoad && !_uiState.value.canLoadMore)) {
            Log.d("MessagesViewModel", "loadHistory: return")
            return
        }
        Log.d("MessagesViewModel", "loadHistory")
        _uiState.update { it.copy(isLoadingHistory = true,) }

        viewModelScope.launch {
            try {
                val fromMessageId = _uiState.value.messages
                .asSequence()
                    .mapNotNull { it.getMessageId() }
                    .lastOrNull()
                    ?: 0L

                var rawMessages = getMessagesWithAlbumBoundaryUseCase(
                    chatId = chatId,
                    fromMessageId = fromMessageId,
                    limit = 50
                )

                Log.d("MessagesViewModel", "Loaded ${rawMessages.size} messages")

                if (rawMessages.isEmpty()) {
                    _uiState.update { it.copy(canLoadMore = false,) }
                    return@launch
                }

                val uniqueSenderIds = rawMessages
                    .map { it.senderId }
                    .toSet()

                val sendersMap = getSendersInfoUseCase(uniqueSenderIds)

                _uiState.update { currentState ->

                    val albumIdOfNewestFirstMessage = rawMessages.firstOrNull()?.mediaAlbumId
                    var currentMessages = currentState.messages

                    for (i in currentMessages.lastIndex downTo currentMessages.lastIndex - 9) {
                        if (i < 0 || albumIdOfNewestFirstMessage == null || currentMessages[i].getAlbumId() != albumIdOfNewestFirstMessage) break
                        when (currentMessages[i]) {
                            is MessageUiItem.MessageItem -> {
                                rawMessages = listOf((currentMessages[i] as MessageUiItem.MessageItem).message) + rawMessages
                                currentMessages = currentMessages.dropLast(1)
                            }
                            is MessageUiItem.AlbumItem -> {
                                val messages = (currentMessages[i] as MessageUiItem.AlbumItem).messages.map {
                                    it.message
                                }
                                rawMessages = messages + rawMessages
                                currentMessages = currentMessages.dropLast(1)
                            }
                            is MessageUiItem.DateSeparator -> break
                        }
                    }

                    val newUiPool = mapAndGroupMessagesToUi(rawMessages, sendersMap)

                    val newestLoadedMessageDate = newUiPool.firstOrNull()?.getMessageDate()
                    val oldestExistingMessageDate = currentMessages.lastOrNull()?.getMessageDate()

                    val separator = if (newestLoadedMessageDate != null && oldestExistingMessageDate != null &&
                        !isSameDay(newestLoadedMessageDate, oldestExistingMessageDate)) {
                        listOf(MessageUiItem.DateSeparator(oldestExistingMessageDate))
                    } else {
                        emptyList()
                    }

                    val newMessages = if (isInitialLoad) newUiPool else currentMessages + separator + newUiPool

                    currentState.copy(
                        messages = newMessages,
                        isLoadingHistory = false,
                        canLoadMore = currentState.canLoadMore,
                    )
                }

            } catch (e: Exception) {
                _uiState.update { it.copy(error = "Ошибка загрузки истории: ${e.message}",) }
            }
        }
    }

    private fun mapAndGroupMessagesToUi(
        rawMessages: List<Message>?,
        sendersMap: Map<Long, Any>?
    ): List<MessageUiItem> {
        if (rawMessages.isNullOrEmpty()) return emptyList()
        val groups = rawMessages.groupBy { it.mediaAlbumId }
        val events = mutableListOf<Pair<Int, MessageUiItem>>()

        groups.forEach { (albumId, messages) ->
            if (albumId == 0L) {
                messages.forEach { msg ->
                    val sender = sendersMap?.get(msg.senderId.getId())
                    val item = MessageUiItem.MessageItem(msg, createAvatarUiState(sender))
                    events.add(msg.date to item)
                }
            } else {
                val sortedMessages = messages.sortedBy { it.id }
                val firstMsg = sortedMessages.first()

                val sender = sendersMap?.get(firstMsg.senderId.getId())
                val avatarState = createAvatarUiState(sender)

                val item = MessageUiItem.AlbumItem(
                    sortedMessages.map { MessageUiItem.MessageItem(it, avatarState) }
                )
                events.add(firstMsg.date to item)
            }
        }
        events.sortByDescending { it.first }
        val finalUiPool = mutableListOf<MessageUiItem>()
        var lastDate: Int? = null

        events.forEach { (date, uiItem) ->
            if (lastDate != null && !isSameDay(date, lastDate)) {
                finalUiPool.add(MessageUiItem.DateSeparator(lastDate))
            }
            finalUiPool.add(uiItem)
            lastDate = date
        }

        return finalUiPool
    }

    private fun createAvatarUiState(senderChat: Any?): AvatarUiState? {
        if (senderChat == null) return null
        return when(senderChat) {
            is Chat -> AvatarUiState(
                senderChat.id,
                senderChat.photo,
                senderChat.title
            )
            is User -> {
                AvatarUiState(
                    senderChat.id,
                    senderChat.profilePhoto?.toChatPhoto(),
                    "${senderChat.firstName} ${senderChat.lastName}"
                )
            }
            else -> null
        }
    }

    private fun sendMessage(content: List<InputMessageContent>) {
        viewModelScope.launch {
            try {
                val sentMessages = sendMessageUseCase(chatId, content)
                processNewMessages(sentMessages)

                _uiState.update { currentState ->
                    currentState.copy(
                        inputMessage = "",
                        selectedMediaUris = emptyList(),
                        attachmentsType = null
                    )
                }

            } catch (e: Exception) {
                _uiState.update { it.copy(error = "Ошибка отправки: ${e.message}",) }
            }
        }
    }

    private fun closeChat(chatId: Long) {
        viewModelScope.launch {
            closeChatUseCase(chatId)
        }
    }

    fun putSelectedMediaUris(uris: List<Uri>) {
        _uiState.update { it.copy(selectedMediaUris = uris,) }
    }

    fun clearSelectedMediaUris(uri: Uri? = null) {
        if (uri != null) {
            _uiState.update { it.copy(selectedMediaUris = it.selectedMediaUris.filter { item -> item != uri },) }
        } else {
            _uiState.update { it.copy(selectedMediaUris = emptyList()) }
        }
    }

    private fun calculateUnreadBelowCount(firstVisibleItemIndex: Int, messages: List<MessageUiItem>, lastReadInboxMessageId: Long?): Int {
        if (lastReadInboxMessageId == null || firstVisibleItemIndex == 0) {
            return 0
        }

        var unreadCount = 0
        for (i in 0 until firstVisibleItemIndex) {
            val uiItem = messages[i]
            when (uiItem) {
                is MessageUiItem.MessageItem -> {
                    if (uiItem.message.id > lastReadInboxMessageId) {
                        unreadCount++
                    }
                }
                is MessageUiItem.AlbumItem -> {
                    uiItem.messages.forEach { albumMessageItem ->
                        if (albumMessageItem.message.id > lastReadInboxMessageId) {
                            unreadCount++
                        }
                    }
                }
                is MessageUiItem.DateSeparator -> {

                }
            }
        }
        return unreadCount
    }
}