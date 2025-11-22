package com.xxcactussell.presentation.mediaviewer.model

import com.xxcactussell.domain.files.model.File
import com.xxcactussell.domain.messages.model.FormattedText
import com.xxcactussell.domain.messages.model.MessageContent

sealed interface MediaPageUiState {
    data object Loading : MediaPageUiState
    data class Content(val path: String, val isVideo: Boolean) : MediaPageUiState
}

data class ViewerUiState(
    val items: List<GalleryItem> = emptyList(),
    val initialIndex: Int = 0,
    val isLoading: Boolean = true
)

data class GalleryItem(
    val content: MessageContent,
    val messageId: Long,
    val file: File,
    val filePreview: File?,
    val isVideo: Boolean,
    val caption: FormattedText? = null
)