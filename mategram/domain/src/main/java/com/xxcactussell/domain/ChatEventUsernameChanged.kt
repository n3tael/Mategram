package com.xxcactussell.domain

data class ChatEventUsernameChanged(
    val oldUsername: String,
    val newUsername: String
) : ChatEventAction
