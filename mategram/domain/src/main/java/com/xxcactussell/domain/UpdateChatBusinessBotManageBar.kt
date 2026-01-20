package com.xxcactussell.domain

data class UpdateChatBusinessBotManageBar(
    val chatId: Long,
    val businessBotManageBar: BusinessBotManageBar? = null
) : Update
