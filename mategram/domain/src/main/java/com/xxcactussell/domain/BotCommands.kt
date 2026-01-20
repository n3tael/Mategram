package com.xxcactussell.domain

data class BotCommands(
    val botUserId: Long,
    val commands: List<BotCommand>
) : Object
