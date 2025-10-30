package com.xxcactussell.presentation.messages.model

import android.net.Uri
import com.xxcactussell.domain.chats.model.Chat
import com.xxcactussell.domain.messages.model.InputMessageContent
import com.xxcactussell.presentation.chats.model.AttachmentEntry

data class MessagesUiState(
    val chat: Chat? = null,
    val messages: List<MessageUiItem> = emptyList(),
    val isLoadingHistory: Boolean = false,
    val isSendingMessage: Boolean = false,
    val canLoadMore: Boolean = true,
    val error: String? = null,
    val attachmentEntries: List<AttachmentEntry> = emptyList(),
    val inputMessage: String = "",
    val selectedMediaUris: List<Uri> = emptyList(),
    val availableRecordingMode: List<RecordingMode> = emptyList(),
    val recordingMode: RecordingMode = RecordingMode.Text,
    val showScrollToBottomButton: Boolean = false,
    val unreadBelowCount: Int = 0,
    val showAttachmentsMenu: Boolean = false,
    val chatStatusStringKey: String? = null,
    val wasOnline: Int = 0
)

sealed interface MessagesEvent {
    data class SendClicked(val content: List<InputMessageContent>) : MessagesEvent
    object LoadMoreHistory : MessagesEvent
    object DismissError : MessagesEvent
    object ShowScrollToBottomButton : MessagesEvent
    object HideScrollToBottomButton : MessagesEvent
    data class MessageLongClicked(val messageId: Long) : MessagesEvent
    data class MessageClicked(val messageId: Long) : MessagesEvent
    data class MessageSwiped(val messageId: Long) : MessagesEvent
    data class MessageRead(val messageId: Long?) : MessagesEvent
    data class UpdateFirstVisibleItemIndex(val index: Int) : MessagesEvent
}

sealed interface InputEvent {
    data class TextChanged(val text: String) : InputEvent
    object SendClicked : InputEvent
    object SwitchRecordingMode : InputEvent
    data class ClearAttachments(val uri: Uri? = null) : InputEvent
    data class TextDropped(val droppedText: String) : InputEvent
    data class MediaDropped(val uri: Uri, val text: String?) : InputEvent
    object OpenAttachmentsMenu : InputEvent
    object CloseAttachmentsMenu : InputEvent
    data class MediaSelected(val uris: List<Uri>) : InputEvent
    data class DocumentsSelected(val uris: List<Uri>) : InputEvent
}

sealed interface RecordingMode {
    object Text : RecordingMode
    object Audio : RecordingMode
    object Video : RecordingMode
}