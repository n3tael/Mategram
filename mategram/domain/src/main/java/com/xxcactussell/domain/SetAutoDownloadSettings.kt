package com.xxcactussell.domain

data class SetAutoDownloadSettings(
    val settings: AutoDownloadSettings,
    val type: NetworkType
) : Function
