package com.xxcactussell.domain

data class SetAutosaveSettings(
    val scope: AutosaveSettingsScope,
    val settings: ScopeAutosaveSettings
) : Function
