package com.xxcactussell.presentation.root.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xxcactussell.domain.files.repository.AddFileToDownloadsUseCase
import com.xxcactussell.domain.files.repository.DownloadFileUseCase
import com.xxcactussell.domain.files.repository.ObserveFileStatusesUseCase
import com.xxcactussell.domain.messages.model.File
import com.xxcactussell.domain.messages.model.Sticker
import com.xxcactussell.domain.root.repository.GetCustomEmojiStickerUseCase
import com.xxcactussell.presentation.localization.LocalizationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RootViewModel @Inject constructor(
    val localizationManager: LocalizationManager,
    observeFileStatuses: ObserveFileStatusesUseCase,
    private val downloadFileUseCase: DownloadFileUseCase,
    private val getCustomEmojiStickerUseCase: GetCustomEmojiStickerUseCase,
    private val addFileToDownloadsUseCase: AddFileToDownloadsUseCase
) : ViewModel() {
    val files: StateFlow<Map<Int, File>> =
        observeFileStatuses()
            .stateIn(viewModelScope, SharingStarted.Companion.Eagerly, emptyMap())

    private val _stickers = MutableStateFlow<Map<Long, Sticker>>(emptyMap())
    val stickers: StateFlow<Map<Long, Sticker>> = _stickers.asStateFlow()

    fun requestStickerInfo(customEmojiId: Long) {
        if (_stickers.value.containsKey(customEmojiId)) {
            return
        }
        viewModelScope.launch {
            getCustomEmojiStickerUseCase(customEmojiId)?.let { sticker ->
                _stickers.update { currentStickers ->
                    currentStickers + (customEmojiId to sticker)
                }
            }
        }
    }

    fun observeFileStatus(fileId: Int): Flow<File?> {
        return files
            .map { it[fileId] }
            .distinctUntilChanged()
    }

    fun downloadFile(fileId: Int?) {
        if (fileId != null) {
            viewModelScope.launch {
                downloadFileUseCase(fileId)
            }
        }
    }

    fun addFileToDownloads(fileId: Int, chatId: Long, messageId: Long, priority: Int = 16) {
        addFileToDownloadsUseCase(fileId, chatId, messageId, priority)
    }
}