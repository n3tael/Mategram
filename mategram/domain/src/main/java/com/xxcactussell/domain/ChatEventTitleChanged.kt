package com.xxcactussell.domain

data class ChatEventTitleChanged(
    val oldTitle: String,
    val newTitle: String
) : ChatEventAction
