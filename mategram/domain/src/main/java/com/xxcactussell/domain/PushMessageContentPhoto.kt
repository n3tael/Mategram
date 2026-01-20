package com.xxcactussell.domain

data class PushMessageContentPhoto(
    val photo: Photo? = null,
    val caption: String,
    val isSecret: Boolean,
    val isPinned: Boolean
) : PushMessageContent
