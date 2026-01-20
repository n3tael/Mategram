package com.xxcactussell.domain

data class UpdateChatBlockList(
    val chatId: Long,
    val blockList: BlockList? = null
) : Update
