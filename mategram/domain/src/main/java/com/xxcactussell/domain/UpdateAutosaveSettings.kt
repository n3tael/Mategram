package com.xxcactussell.domain

data class UpdateAutosaveSettings(
    val scope: AutosaveSettingsScope,
    val settings: ScopeAutosaveSettings? = null
) : Update
