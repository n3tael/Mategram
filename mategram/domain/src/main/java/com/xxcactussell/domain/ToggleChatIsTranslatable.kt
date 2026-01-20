package com.xxcactussell.domain

data class ToggleChatIsTranslatable(
    val chatId: Long,
    val isTranslatable: Boolean
) : Function
