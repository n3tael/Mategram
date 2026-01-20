package com.xxcactussell.domain

data class ChatActionBarJoinRequest(
    val title: String,
    val isChannel: Boolean,
    val requestDate: Int
) : ChatActionBar
