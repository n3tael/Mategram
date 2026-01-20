package com.xxcactussell.domain

data class AutoDownloadSettings(
    val isAutoDownloadEnabled: Boolean,
    val maxPhotoFileSize: Int,
    val maxVideoFileSize: Long,
    val maxOtherFileSize: Long,
    val videoUploadBitrate: Int,
    val preloadLargeVideos: Boolean,
    val preloadNextAudio: Boolean,
    val preloadStories: Boolean,
    val useLessDataForCalls: Boolean
) : Object
