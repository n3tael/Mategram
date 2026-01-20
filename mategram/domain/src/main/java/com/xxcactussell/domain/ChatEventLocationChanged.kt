package com.xxcactussell.domain

data class ChatEventLocationChanged(
    val oldLocation: ChatLocation? = null,
    val newLocation: ChatLocation? = null
) : ChatEventAction
