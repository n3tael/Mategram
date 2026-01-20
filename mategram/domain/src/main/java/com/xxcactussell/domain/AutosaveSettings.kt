package com.xxcactussell.domain

data class AutosaveSettings(
    val privateChatSettings: ScopeAutosaveSettings,
    val groupSettings: ScopeAutosaveSettings,
    val channelSettings: ScopeAutosaveSettings,
    val exceptions: List<AutosaveSettingsException>
) : Object
