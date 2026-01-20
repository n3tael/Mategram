package com.xxcactussell.domain

data class ReportStory(
    val storyPosterChatId: Long,
    val storyId: Int,
    val optionId: ByteArray,
    val text: String
) : Function
