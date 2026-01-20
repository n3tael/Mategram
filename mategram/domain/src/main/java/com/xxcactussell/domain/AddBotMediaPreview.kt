package com.xxcactussell.domain

data class AddBotMediaPreview(
    val botUserId: Long,
    val languageCode: String,
    val content: InputStoryContent
) : Function
