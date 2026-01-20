package com.xxcactussell.domain

data class DeleteCommands(
    val scope: BotCommandScope,
    val languageCode: String
) : Function
