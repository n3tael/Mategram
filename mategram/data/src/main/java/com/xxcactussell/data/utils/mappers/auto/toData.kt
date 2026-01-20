package com.xxcactussell.data.utils.mappers.auto

import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun AutoDownloadSettings.toData(): TdApi.AutoDownloadSettings = TdApi.AutoDownloadSettings(
    this.isAutoDownloadEnabled,
    this.maxPhotoFileSize,
    this.maxVideoFileSize,
    this.maxOtherFileSize,
    this.videoUploadBitrate,
    this.preloadLargeVideos,
    this.preloadNextAudio,
    this.preloadStories,
    this.useLessDataForCalls
)

fun AutoDownloadSettingsPresets.toData(): TdApi.AutoDownloadSettingsPresets = TdApi.AutoDownloadSettingsPresets(
    this.low.toData(),
    this.medium.toData(),
    this.high.toData()
)

