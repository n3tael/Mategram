package com.xxcactussell.domain

data class ChatEventDescriptionChanged(
    val oldDescription: String,
    val newDescription: String
) : ChatEventAction
