package com.xxcactussell.domain

data class ChatEventHasProtectedContentToggled(
    val hasProtectedContent: Boolean
) : ChatEventAction
