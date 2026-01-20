package com.xxcactussell.presentation.messages.viewmodel

import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.xxcactussell.domain.Chat
import com.xxcactussell.domain.ChatMemberStatusAdministrator
import com.xxcactussell.domain.ChatMemberStatusCreator
import com.xxcactussell.domain.ChatType
import com.xxcactussell.domain.ChatTypeBasicGroup
import com.xxcactussell.domain.ChatTypePrivate
import com.xxcactussell.domain.ChatTypeSecret
import com.xxcactussell.domain.ChatTypeSupergroup
import com.xxcactussell.domain.InputMessageContent
import com.xxcactussell.domain.MessageSource
import com.xxcactussell.domain.MessageSourceChatHistory
import com.xxcactussell.domain.ReactionType
import com.xxcactussell.domain.User
import com.xxcactussell.domain.UserStatus
import com.xxcactussell.domain.UserStatusEmpty
import com.xxcactussell.domain.UserStatusLastMonth
import com.xxcactussell.domain.UserStatusLastWeek
import com.xxcactussell.domain.UserStatusOffline
import com.xxcactussell.domain.UserStatusOnline
import com.xxcactussell.domain.UserStatusRecently
import com.xxcactussell.domain.toChatPhotoInfo
import com.xxcactussell.repositories.chats.model.ChatStatus
import com.xxcactussell.repositories.chats.repository.ObserveChatStatusesUseCase
import com.xxcactussell.repositories.files.repository.CancelDownloadFileUseCase
import com.xxcactussell.repositories.messages.model.MessageListItem
import com.xxcactussell.repositories.messages.model.getDate
import com.xxcactussell.repositories.messages.repository.AddReactionToMessageUseCase
import com.xxcactussell.repositories.messages.repository.BuildMessageContentUseCase
import com.xxcactussell.repositories.messages.repository.CloseChatUseCase
import com.xxcactussell.repositories.messages.repository.GetChatActionFlowUseCase
import com.xxcactussell.repositories.messages.repository.GetChatFlowUseCase
import com.xxcactussell.repositories.messages.repository.GetUserUseCase
import com.xxcactussell.repositories.messages.repository.LoadMoreHistoryUseCase
import com.xxcactussell.repositories.messages.repository.MarkMessageAsRead
import com.xxcactussell.repositories.messages.repository.ObserveLastReadOutboxMessageUseCase
import com.xxcactussell.repositories.messages.repository.OpenChatUseCase
import com.xxcactussell.repositories.messages.repository.RemoveReactionFromMessageUseCase
import com.xxcactussell.repositories.messages.repository.SendMessageUseCase
import com.xxcactussell.mategram.presentation.R
import com.xxcactussell.presentation.chats.model.AttachmentEntry
import com.xxcactussell.presentation.chats.model.AvatarUiState
import com.xxcactussell.presentation.chats.model.ChatEffect
import com.xxcactussell.presentation.messages.model.AttachmentEvent
import com.xxcactussell.presentation.messages.model.InputEvent
import com.xxcactussell.presentation.messages.model.MessageUiItem
import com.xxcactussell.presentation.messages.model.MessagesEvent
import com.xxcactussell.presentation.messages.model.MessagesEvent.CancelDownloadFile
import com.xxcactussell.presentation.messages.model.MessagesEvent.DismissError
import com.xxcactussell.presentation.messages.model.MessagesEvent.DownloadFile
import com.xxcactussell.presentation.messages.model.MessagesEvent.HideScrollToBottomButton
import com.xxcactussell.presentation.messages.model.MessagesEvent.LoadMoreHistory
import com.xxcactussell.presentation.messages.model.MessagesEvent.MessageClicked
import com.xxcactussell.presentation.messages.model.MessagesEvent.MessageLongClicked
import com.xxcactussell.presentation.messages.model.MessagesEvent.MessageRead
import com.xxcactussell.presentation.messages.model.MessagesEvent.MessageSwiped
import com.xxcactussell.presentation.messages.model.MessagesEvent.OpenFile
import com.xxcactussell.presentation.messages.model.MessagesEvent.SendClicked
import com.xxcactussell.presentation.messages.model.MessagesEvent.ShowScrollToBottomButton
import com.xxcactussell.presentation.messages.model.MessagesEvent.UpdateFirstVisibleItemIndex
import com.xxcactussell.presentation.messages.model.MessagesUiState
import com.xxcactussell.presentation.messages.model.RecordingMode
import com.xxcactussell.presentation.messages.model.getMessage
import com.xxcactussell.presentation.messages.model.getMessageId
import com.xxcactussell.presentation.root.workers.DownloadFileWorker
import com.xxcactussell.presentation.tools.FileOpener
import com.xxcactussell.presentation.tools.isSameDay
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


@AssistedFactory
interface MessagesViewModelFactory {
    fun create(chatId: Long): MessagesViewModel
}

@HiltViewModel(assistedFactory = MessagesViewModelFactory::class)
class MessagesViewModel @AssistedInject constructor(
    private val openChatUseCase: OpenChatUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val observeChatStatuses: ObserveChatStatusesUseCase,
    private val buildMessageContent: BuildMessageContentUseCase,
    private val addReactionToMessage: AddReactionToMessageUseCase,
    private val removeReactionFromMessage: RemoveReactionFromMessageUseCase,
    private val cancelDownloadFileUseCase: CancelDownloadFileUseCase,
    private val workManager: WorkManager,
    private val getUser: GetUserUseCase,
    private val markMessageAsRead: MarkMessageAsRead,
    private val observeLastReadOutboxMessage: ObserveLastReadOutboxMessageUseCase,
    private val getChatFlow: GetChatFlowUseCase,
    private val getChatAction: GetChatActionFlowUseCase,
    private val closeChat: CloseChatUseCase,
    private val loadMoreHistory: LoadMoreHistoryUseCase,
    @Assisted private val chatId: Long
) : ViewModel() {
    private val _uiState = MutableStateFlow(MessagesUiState())
    val uiState: StateFlow<MessagesUiState> = _uiState.asStateFlow()

    private val _effects = Channel<ChatEffect>()
    val effects = _effects.receiveAsFlow()

    private var lastKnownFirstVisibleItemIndex: Int = 0

    init {
        observeMessagesFlow()
        loadMoreHistory(chatId)
        viewModelScope.launch {
            try {
                val chat = openChatUseCase(chatId)
                if (chat != null) {
                    val attachmentEntries = createAttachmentEntries(chat)
                    val user = if (chat.type is ChatTypePrivate) {
                        getUser((chat.type as ChatTypePrivate).userId)
                    } else {
                        null
                    }
                    _uiState.update {
                        it.copy(
                            chat = chat,
                            user = user,
                            attachmentEntries = attachmentEntries,
                        )
                    }
                    setupChatStatusObserver(chat)
                } else {
                    _uiState.update { it.copy(error = "Чат не найден") }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = "Ошибка загрузки чата: ${e.message}") }
            }
        }
        observeReadState()
    }

    private fun observeMessagesFlow() {
        getChatFlow(chatId)
            .onEach { domainItems ->
                _uiState.update { currentState ->
                    Log.d("MessagesViewModel", "observeMessagesFlow: ${domainItems.size}")

                    val uiItems = mapDomainToUi(domainItems)

                    currentState.copy(
                        messages = uiItems,
                        isLoadingHistory = false,
                        canLoadMore = true
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    private fun mapDomainToUi(items: List<MessageListItem>): List<MessageUiItem> {
        if (items.isEmpty()) return emptyList()

        Log.d("MessagesViewModel", "mapDomainToUi: ${items.size}")

        val uiItems = mutableListOf<MessageUiItem>()
        var lastDate: Int? = null

        items.forEach { item ->
            val date = item.getDate()

            if (lastDate != null && !isSameDay(date, lastDate)) {
                uiItems.add(MessageUiItem.DateSeparator(lastDate))
            }

            val uiItem = when(item) {
                is MessageListItem.MessageItem -> {
                    MessageUiItem.MessageItem(
                        item.message,
                        createAvatarUiState(item.sender)
                    )
                }
                is MessageListItem.AlbumItem -> {
                    val messagesWithAvatars = item.messages.map { msg ->
                        MessageUiItem.MessageItem(msg, createAvatarUiState(item.sender))
                    }
                    MessageUiItem.AlbumItem(messagesWithAvatars)
                }
            }

            uiItems.add(uiItem)
            lastDate = date
        }

        if (lastDate != null) {
            uiItems.add(MessageUiItem.DateSeparator(lastDate))
        }

        return uiItems
    }

    private fun observeReadState() {
        observeLastReadOutboxMessage()
            .onEach { (chatId, messageId) ->
                if (chatId == _uiState.value.chat?.id) {
                    _uiState.update {
                        it.copy(chat = it.chat?.copy(lastReadOutboxMessageId = messageId))
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    private fun setupChatStatusObserver(chat: Chat) {
        when (chat.type) {
            is ChatTypeSecret, is ChatTypePrivate -> observeStatus()
            is ChatTypeBasicGroup -> _uiState.update { it.copy(chatStatusStringKey = "Members") }
            is ChatTypeSupergroup -> _uiState.update {
                it.copy(chatStatusStringKey = if ((chat.type as ChatTypeSupergroup).isChannel) "Subscribers" else "Members")
            }
        }
    }

    private fun observeStatus() {
        observeChatStatuses()
            .onEach { status ->
                val chatId = _uiState.value.chat?.id ?: return@onEach
                val chatStatus = status[chatId]
                if (chatStatus != null) {
                    _uiState.update {
                        val wasOnline = if (chatStatus is UserStatusOffline) chatStatus.wasOnline else 0
                        it.copy(
                            chatStatusStringKey = when(chatStatus) {
                                is UserStatusEmpty -> "ALongTimeAgo"
                                is UserStatusLastMonth -> "WithinAMonth"
                                is UserStatusLastWeek -> "WithinAWeek"
                                is UserStatusOffline -> "LastSeenFormatted"
                                is UserStatusOnline -> "Online"
                                is UserStatusRecently -> "Lately"
                            },
                            wasOnline = wasOnline
                        )
                    }
                }
            }
            .launchIn(viewModelScope)

        getChatAction(chatId)
            .onEach { chatAction ->
                _uiState.update { currentState ->
                    currentState.copy(chatAction = chatAction)
                }
            }
            .launchIn(viewModelScope)
    }

    override fun onCleared() {
        super.onCleared()
        closeChat(chatId)
    }

    fun onEvent(event: Any) {
        when (event) {
            is MessagesEvent -> onMessagesEvent(event)
            is InputEvent -> onInputEvent(event)
            is AttachmentEvent -> onAttachmentEvent(event)
        }
    }

    private fun onMessagesEvent(event: MessagesEvent) {
        when (event) {
            is SendClicked -> sendMessage(event.content)
            is LoadMoreHistory -> loadMoreHistory(chatId)
            is DismissError -> _uiState.update { it.copy(error = null) }
            is MessageClicked -> { if (_uiState.value.messageIdWithDateShown == event.messageId) _uiState.update { it.copy(messageIdWithDateShown = null) } else _uiState.update { it.copy(messageIdWithDateShown = event.messageId) } }
            is MessageLongClicked -> { /* TODO */ }
            is MessageSwiped -> { /* TODO */ }
            is ShowScrollToBottomButton -> _uiState.update { it.copy(showScrollToBottomButton = true) }
            is HideScrollToBottomButton -> _uiState.update { it.copy(showScrollToBottomButton = false) }
            is MessageRead -> handleMessageRead(event.messageId)
            is UpdateFirstVisibleItemIndex -> {
                lastKnownFirstVisibleItemIndex = event.index
                recalculateUnreadCount()
            }
            is CancelDownloadFile -> cancelDownload(event.fileId)
            is DownloadFile -> downloadFile(event.fileId, event.fileName)
            is OpenFile -> openFile(event.context, event.fileName)
            is MessagesEvent.ToggleReaction -> toggleReaction(event.chatId, event.messageId, event.reactionType)
            is MessagesEvent.ReplyToSelected -> _uiState.update { it.copy(messageToReplay = event.message) }
        }
    }

    private fun handleMessageRead(messageId: Long?) {
        if (messageId != null && _uiState.value.chat?.id != null) {
            markMessageAsRead(_uiState.value.chat!!.id, messageId, MessageSourceChatHistory)
            _uiState.update { currentState ->
                val currentChat = currentState.chat
                if (currentChat != null && messageId > currentChat.lastReadInboxMessageId) {
                    currentState.copy(
                        chat = currentChat.copy(lastReadInboxMessageId = messageId)
                    )
                } else {
                    currentState
                }
            }
            recalculateUnreadCount()
        }
    }

    private fun recalculateUnreadCount() {
        _uiState.update { currentState ->
            val unreadCount = calculateUnreadBelowCount(
                firstVisibleItemIndex = lastKnownFirstVisibleItemIndex,
                messages = currentState.messages,
                lastReadInboxMessageId = currentState.chat?.lastReadInboxMessageId
            )
            currentState.copy(unreadBelowCount = unreadCount)
        }
    }

    private fun sendMessage(content: List<InputMessageContent>) {
        viewModelScope.launch {
            try {
                sendMessageUseCase(chatId, content, uiState.value.messageToReplay?.getMessageId() ?: 0L)

                _uiState.update { currentState ->
                    currentState.copy(
                        inputMessage = "",
                        selectedMediaUris = emptyList(),
                        attachmentsType = null,
                        messageToReplay = null
                    )
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = "Ошибка отправки: ${e.message}") }
            }
        }
    }

    private fun onInputEvent(event: InputEvent) {
        when (event) {
            is InputEvent.SendClicked -> {
                val currentState = _uiState.value
                if (currentState.inputMessage.isNotBlank() || currentState.selectedMediaUris.isNotEmpty()) {
                    viewModelScope.launch {
                        val contents = buildMessageContent(
                            currentState.selectedMediaUris,
                            currentState.inputMessage,
                            emptyList(),
                            currentState.attachmentsType
                        )
                        onMessagesEvent(SendClicked(contents))
                    }
                }
            }
            is InputEvent.TextChanged -> _uiState.update { it.copy(inputMessage = event.text) }
            is InputEvent.ClearAttachments -> clearSelectedMediaUris(event.uri)
            is InputEvent.SwitchRecordingMode -> switchRecordingMode()
            is InputEvent.OpenAttachmentsMenu -> _uiState.update { it.copy(showAttachmentsMenu = true) }
            is InputEvent.CloseAttachmentsMenu -> _uiState.update { it.copy(showAttachmentsMenu = false) }
            is InputEvent.DocumentsSelected -> _uiState.update { it.copy(selectedMediaUris = event.uris) }
            is InputEvent.MediaSelected -> _uiState.update { it.copy(selectedMediaUris = event.uris, attachmentsType = "Media") }
            else -> {}
        }
    }

    private fun switchRecordingMode() {
        _uiState.update { currentState ->
            val nextMode = when (currentState.recordingMode) {
                RecordingMode.Text -> currentState.availableRecordingMode.firstOrNull() ?: RecordingMode.Text
                RecordingMode.Audio -> if (currentState.availableRecordingMode.contains(RecordingMode.Video)) RecordingMode.Video else RecordingMode.Text
                RecordingMode.Video -> if (currentState.availableRecordingMode.contains(RecordingMode.Audio)) RecordingMode.Audio else RecordingMode.Text
            }
            currentState.copy(recordingMode = nextMode)
        }
    }

    private fun onAttachmentEvent(event: AttachmentEvent) {
        viewModelScope.launch {
            when (event) {
                is AttachmentEvent.ChatGallery -> {
                    _effects.send(ChatEffect.LaunchMediaPicker)
                    _uiState.update { it.copy(showAttachmentsMenu = false) }
                }
                is AttachmentEvent.ChatDocument -> {
                    _effects.send(ChatEffect.LaunchDocumentPicker)
                    _uiState.update { it.copy(showAttachmentsMenu = false) }
                }
                is AttachmentEvent.AttachMusic -> {
                    _effects.send(ChatEffect.LaunchMusicPicker)
                    _uiState.update { it.copy(showAttachmentsMenu = false) }
                }
                else -> { /* TODO */ }
            }
        }
    }

    private fun createAvatarUiState(sender: Any?): AvatarUiState? {
        if (sender == null) return null
        return when(sender) {
            is Chat -> AvatarUiState(sender.id, sender.photo, sender.title)
            is User -> AvatarUiState(sender.id, sender.profilePhoto?.toChatPhotoInfo(), "${sender.firstName} ${sender.lastName}")
            else -> null
        }
    }

    private fun createAttachmentEntries(chat: Chat): List<AttachmentEntry> {
        val attachmentEntries = mutableListOf<AttachmentEntry>()
        val recordingMode = mutableListOf<RecordingMode>()
        val permissions = chat.permissions
        val isAdmin = chat.myMemberStatus is ChatMemberStatusCreator || (chat.myMemberStatus is ChatMemberStatusAdministrator && (chat.myMemberStatus as ChatMemberStatusAdministrator).rights.canPostMessages)

        fun can(condition: Boolean) = condition || isAdmin

        if (can(permissions.canSendPhotos || permissions.canSendVideos)) {
            attachmentEntries.add(AttachmentEntry(R.drawable.photo_library_24px, "ChatGallery", AttachmentEvent.ChatGallery))
        }
        if (can(permissions.canSendDocuments)) {
            attachmentEntries.add(AttachmentEntry(R.drawable.file_present_24px, "ChatDocument", AttachmentEvent.ChatDocument))
        }
        if (can(permissions.canSendPolls)) {
            attachmentEntries.add(AttachmentEntry(R.drawable.bar_chart_24px, "Poll", AttachmentEvent.Poll))
            attachmentEntries.add(AttachmentEntry(R.drawable.checklist_24px, "Todo", AttachmentEvent.Todo))
        }
        if (can(permissions.canSendAudios)) {
            attachmentEntries.add(AttachmentEntry(R.drawable.music_note_2_24px, "AttachMusic", AttachmentEvent.AttachMusic))
        }
        if (can(permissions.canSendBasicMessages)) {
            attachmentEntries.add(AttachmentEntry(R.drawable.contact_phone_24px, "AttachContact", AttachmentEvent.AttachContact))
            attachmentEntries.add(AttachmentEntry(R.drawable.location_on_24px, "ChatLocation", AttachmentEvent.ChatLocation))
        }

        if (can(permissions.canSendVoiceNotes)) recordingMode.add(RecordingMode.Audio)
        if (can(permissions.canSendVideoNotes)) recordingMode.add(RecordingMode.Video)

        _uiState.update {
            it.copy(
                recordingMode = recordingMode.firstOrNull() ?: RecordingMode.Text,
                availableRecordingMode = recordingMode
            )
        }

        return attachmentEntries
    }

    fun clearSelectedMediaUris(uri: Uri? = null) {
        if (uri != null) {
            _uiState.update { it.copy(selectedMediaUris = it.selectedMediaUris.filter { item -> item != uri }) }
        } else {
            _uiState.update { it.copy(selectedMediaUris = emptyList()) }
        }
    }

    private fun calculateUnreadBelowCount(firstVisibleItemIndex: Int, messages: List<MessageUiItem>, lastReadInboxMessageId: Long?): Int {
        if (lastReadInboxMessageId == null || firstVisibleItemIndex == 0) return 0
        var unreadCount = 0
        for (i in 0 until firstVisibleItemIndex) {
            when (val uiItem = messages.getOrNull(i)) {
                is MessageUiItem.MessageItem -> if (uiItem.message.id > lastReadInboxMessageId) unreadCount++
                is MessageUiItem.AlbumItem -> uiItem.messages.forEach { if (it.message.id > lastReadInboxMessageId) unreadCount++ }
                else -> {}
            }
        }
        return unreadCount
    }

    fun downloadFile(fileId: Int, fileName: String) {
        val inputData = workDataOf(DownloadFileWorker.INPUT_FILE_TD_ID to fileId, DownloadFileWorker.INPUT_FILE_NAME to fileName)
        val request = OneTimeWorkRequestBuilder<DownloadFileWorker>()
            .setInputData(inputData)
            .addTag("downloading")
            .build()
        workManager.enqueueUniqueWork("download_$fileId", ExistingWorkPolicy.KEEP, request)
    }

    fun cancelDownload(fileId: Int) {
        workManager.cancelUniqueWork("download_$fileId")
        viewModelScope.launch { cancelDownloadFileUseCase(fileId) }
    }

    fun openFile(context: android.content.Context, fileName: String) {
        val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val file = java.io.File(downloadsDir, "Mategram/${fileName}")
        FileOpener.openFile(context, file)
    }

    fun toggleReaction(chatId: Long, messageId: Long, reactionType: ReactionType) {
        if (uiState.value.getMessage(messageId)?.interactionInfo?.reactions?.reactions?.firstOrNull { it.type == reactionType && it.isChosen } != null) {
            removeReactionFromMessage(chatId, messageId, reactionType)
        } else {
            addReactionToMessage(chatId, messageId, reactionType)
        }
    }
}