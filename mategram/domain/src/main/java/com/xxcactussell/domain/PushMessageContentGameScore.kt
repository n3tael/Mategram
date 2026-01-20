package com.xxcactussell.domain

data class PushMessageContentGameScore(
    val title: String,
    val score: Int,
    val isPinned: Boolean
) : PushMessageContent
