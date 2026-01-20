package com.xxcactussell.domain

data class ChatEventBackgroundChanged(
    val oldBackground: ChatBackground? = null,
    val newBackground: ChatBackground? = null
) : ChatEventAction
