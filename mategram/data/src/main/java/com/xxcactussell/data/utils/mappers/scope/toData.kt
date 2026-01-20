package com.xxcactussell.data.utils.mappers.scope

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun ScopeAutosaveSettings.toData(): TdApi.ScopeAutosaveSettings = TdApi.ScopeAutosaveSettings(
    this.autosavePhotos,
    this.autosaveVideos,
    this.maxVideoFileSize
)

fun ScopeNotificationSettings.toData(): TdApi.ScopeNotificationSettings = TdApi.ScopeNotificationSettings(
    this.muteFor,
    this.soundId,
    this.showPreview,
    this.useDefaultMuteStories,
    this.muteStories,
    this.storySoundId,
    this.showStoryPoster,
    this.disablePinnedMessageNotifications,
    this.disableMentionNotifications
)

