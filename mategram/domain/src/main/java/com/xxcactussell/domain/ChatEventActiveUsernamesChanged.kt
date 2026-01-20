package com.xxcactussell.domain

data class ChatEventActiveUsernamesChanged(
    val oldUsernames: List<String>,
    val newUsernames: List<String>
) : ChatEventAction
