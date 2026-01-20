package com.xxcactussell.domain

data class AutoDownloadSettingsPresets(
    val low: AutoDownloadSettings,
    val medium: AutoDownloadSettings,
    val high: AutoDownloadSettings
) : Object
