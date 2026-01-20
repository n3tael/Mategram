package com.xxcactussell.domain

data class SetCommands(
    val scope: BotCommandScope,
    val languageCode: String,
    val commands: List<BotCommand>
) : Function
