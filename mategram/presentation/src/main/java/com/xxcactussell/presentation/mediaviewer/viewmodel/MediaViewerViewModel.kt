package com.xxcactussell.presentation.mediaviewer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xxcactussell.domain.files.model.File
import com.xxcactussell.domain.messages.model.Message
import com.xxcactussell.domain.messages.model.MessageContent
import com.xxcactussell.domain.messages.repository.GetChatMediaHistoryUseCase
import com.xxcactussell.presentation.mediaviewer.model.GalleryItem
import com.xxcactussell.presentation.mediaviewer.model.MediaPageUiState
import com.xxcactussell.presentation.mediaviewer.model.ViewerUiState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


@AssistedFactory
interface MediaViewerViewModelFactory {
    fun create(@Assisted("messageId") messageId: Long, @Assisted("chatId") chatId: Long): MediaViewerViewModel
}

@HiltViewModel(assistedFactory = MediaViewerViewModelFactory::class)
class MediaViewerViewModel @AssistedInject constructor(
    private val getChatMediaHistoryUseCase: GetChatMediaHistoryUseCase,
    @Assisted("messageId") private val messageId: Long,
    @Assisted("chatId") private val chatId: Long
) : ViewModel() {
    private val _state = MutableStateFlow(ViewerUiState())
    val state = _state.asStateFlow()

    private val _isUiVisible = MutableStateFlow(true)
    val isUiVisible = _isUiVisible.asStateFlow()

    init {
        loadMediaContext(chatId, messageId)
    }

    fun loadMediaContext(chatId: Long, startMessageId: Long) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val messages = getChatMediaHistoryUseCase(chatId, startMessageId, limit = 50)

            val galleryItems = messages.mapNotNull { msg -> mapMessageToGalleryItem(msg) }

            val startIndex = galleryItems.indexOfFirst { it.messageId == startMessageId }.coerceAtLeast(0)

            _state.update {
                it.copy(
                    items = galleryItems,
                    initialIndex = startIndex,
                    isLoading = false
                )
            }
        }
    }

    private fun mapMessageToGalleryItem(message: Message): GalleryItem? {
        return when (val content = message.content) {
            is MessageContent.MessagePhoto -> GalleryItem(
                content = content,
                messageId = message.id,
                file = content.photo.sizes.maxBy { it.width }.photo,
                filePreview = content.photo.sizes.minBy { it.width }.photo,
                isVideo = false,
                caption = content.caption,
            )
            is MessageContent.MessageVideo -> GalleryItem(
                content = content,
                messageId = message.id,
                file = content.video.video,
                filePreview = content.cover?.sizes?.maxByOrNull { it.width }?.photo
                    ?: content.video.thumbnail?.file,
                isVideo = true,
            )
            else -> null
        }
    }

    fun toggleUiVisibility() {
        _isUiVisible.update { !it }
    }

    fun getUiStateForFile(file: File, isVideo: Boolean, supportStreaming: Boolean = false): MediaPageUiState {
        val path = file.local.path
        return if (supportStreaming) {
            MediaPageUiState.Content(
                path = path,
                isVideo = true
            )
        } else {
            if (file.local.isDownloadingComplete && path.isNotEmpty()) {
                MediaPageUiState.Content(path, isVideo)
            } else {
                MediaPageUiState.Loading
            }
        }
    }
}