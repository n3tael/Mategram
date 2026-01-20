package com.xxcactussell.domain

data class EditBotMediaPreview(
    val botUserId: Long,
    val languageCode: String,
    val fileId: Int,
    val content: InputStoryContent
) : Function
