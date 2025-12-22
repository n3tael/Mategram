package com.xxcactussell.domain.chats.model

import com.xxcactussell.domain.messages.model.AnimatedEmoji

sealed interface ChatAction {
    object Typing: ChatAction
    object RecordingVideo: ChatAction
    object UploadingVideo: ChatAction
    object RecordingVoiceNote: ChatAction
    object UploadingVoiceNote: ChatAction
    object UploadingPhoto: ChatAction
    object UploadingDocument: ChatAction
    object ChoosingSticker: ChatAction
    object ChoosingLocation: ChatAction
    object ChoosingContact: ChatAction
    object StartPlayingGame: ChatAction
    object RecordingVideoNote: ChatAction
    object UploadingVideoNote: ChatAction
    data class WatchingAnimations(val emoji: String = ""): ChatAction
    object Cancel: ChatAction
}