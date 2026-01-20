package com.xxcactussell.domain

data class ChatFolderInviteLink(
    val inviteLink: String,
    val name: String,
    val chatIds: LongArray
) : Object
