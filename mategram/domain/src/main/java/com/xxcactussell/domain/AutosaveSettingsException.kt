package com.xxcactussell.domain

data class AutosaveSettingsException(
    val chatId: Long,
    val settings: ScopeAutosaveSettings
) : Object
