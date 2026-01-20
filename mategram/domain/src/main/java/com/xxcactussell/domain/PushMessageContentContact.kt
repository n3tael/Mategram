package com.xxcactussell.domain

data class PushMessageContentContact(
    val name: String,
    val isPinned: Boolean
) : PushMessageContent
