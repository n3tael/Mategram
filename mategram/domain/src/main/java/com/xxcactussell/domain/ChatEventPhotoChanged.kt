package com.xxcactussell.domain

data class ChatEventPhotoChanged(
    val oldPhoto: ChatPhoto? = null,
    val newPhoto: ChatPhoto? = null
) : ChatEventAction
