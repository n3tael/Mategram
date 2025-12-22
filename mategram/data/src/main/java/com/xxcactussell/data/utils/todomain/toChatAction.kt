package com.xxcactussell.data.utils.todomain

import com.xxcactussell.domain.chats.model.ChatAction
import org.drinkless.tdlib.TdApi

fun TdApi.ChatAction.toDomain() : ChatAction = when (this) {
    is TdApi.ChatActionTyping -> ChatAction.Typing
    is TdApi.ChatActionRecordingVideo -> ChatAction.RecordingVideo
    is TdApi.ChatActionRecordingVoiceNote -> ChatAction.RecordingVoiceNote
    is TdApi.ChatActionUploadingVideo -> ChatAction.UploadingVideo
    is TdApi.ChatActionUploadingVoiceNote -> ChatAction.UploadingVoiceNote
    is TdApi.ChatActionUploadingPhoto -> ChatAction.UploadingPhoto
    is TdApi.ChatActionUploadingDocument -> ChatAction.UploadingDocument
    is TdApi.ChatActionChoosingLocation -> ChatAction.ChoosingLocation
    is TdApi.ChatActionChoosingContact -> ChatAction.ChoosingContact
    is TdApi.ChatActionStartPlayingGame -> ChatAction.StartPlayingGame
    is TdApi.ChatActionRecordingVideoNote -> ChatAction.RecordingVideoNote
    is TdApi.ChatActionUploadingVideoNote -> ChatAction.UploadingVideoNote
    is TdApi.ChatActionWatchingAnimations -> ChatAction.WatchingAnimations(this.emoji)
    is TdApi.ChatActionChoosingSticker -> ChatAction.ChoosingSticker
    else -> ChatAction.Cancel
}