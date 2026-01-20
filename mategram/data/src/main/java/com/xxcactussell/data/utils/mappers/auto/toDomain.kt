package com.xxcactussell.data.utils.mappers.auto

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.AutoDownloadSettings.toDomain(): AutoDownloadSettings = AutoDownloadSettings(
    isAutoDownloadEnabled = this.isAutoDownloadEnabled,
    maxPhotoFileSize = this.maxPhotoFileSize,
    maxVideoFileSize = this.maxVideoFileSize,
    maxOtherFileSize = this.maxOtherFileSize,
    videoUploadBitrate = this.videoUploadBitrate,
    preloadLargeVideos = this.preloadLargeVideos,
    preloadNextAudio = this.preloadNextAudio,
    preloadStories = this.preloadStories,
    useLessDataForCalls = this.useLessDataForCalls
)

fun TdApi.AutoDownloadSettingsPresets.toDomain(): AutoDownloadSettingsPresets = AutoDownloadSettingsPresets(
    low = this.low.toDomain(),
    medium = this.medium.toDomain(),
    high = this.high.toDomain()
)

