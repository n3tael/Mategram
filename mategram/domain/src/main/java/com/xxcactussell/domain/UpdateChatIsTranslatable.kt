package com.xxcactussell.domain

data class UpdateChatIsTranslatable(
    val chatId: Long,
    val isTranslatable: Boolean
) : Update
