package com.xxcactussell.domain

data class UpdateSuggestedActions(
    val addedActions: List<SuggestedAction>,
    val removedActions: List<SuggestedAction>
) : Update
