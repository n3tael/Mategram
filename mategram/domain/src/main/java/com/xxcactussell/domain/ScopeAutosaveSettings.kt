package com.xxcactussell.domain

data class ScopeAutosaveSettings(
    val autosavePhotos: Boolean,
    val autosaveVideos: Boolean,
    val maxVideoFileSize: Long
) : Object
