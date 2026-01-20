package com.xxcactussell.domain

data class ToggleSupergroupHasAutomaticTranslation(
    val supergroupId: Long,
    val hasAutomaticTranslation: Boolean
) : Function
