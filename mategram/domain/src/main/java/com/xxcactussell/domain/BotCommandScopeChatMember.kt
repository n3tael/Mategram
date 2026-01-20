package com.xxcactussell.domain

data class BotCommandScopeChatMember(
    val chatId: Long,
    val userId: Long
) : BotCommandScope
