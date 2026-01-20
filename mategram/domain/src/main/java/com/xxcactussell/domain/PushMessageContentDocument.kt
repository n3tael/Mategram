package com.xxcactussell.domain

data class PushMessageContentDocument(
    val document: Document? = null,
    val isPinned: Boolean
) : PushMessageContent
