package com.xxcactussell.data.utils.mappers.scope

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.ScopeAutosaveSettings.toDomain(): ScopeAutosaveSettings = ScopeAutosaveSettings(
    autosavePhotos = this.autosavePhotos,
    autosaveVideos = this.autosaveVideos,
    maxVideoFileSize = this.maxVideoFileSize
)

fun TdApi.ScopeNotificationSettings.toDomain(): ScopeNotificationSettings = ScopeNotificationSettings(
    muteFor = this.muteFor,
    soundId = this.soundId,
    showPreview = this.showPreview,
    useDefaultMuteStories = this.useDefaultMuteStories,
    muteStories = this.muteStories,
    storySoundId = this.storySoundId,
    showStoryPoster = this.showStoryPoster,
    disablePinnedMessageNotifications = this.disablePinnedMessageNotifications,
    disableMentionNotifications = this.disableMentionNotifications
)

