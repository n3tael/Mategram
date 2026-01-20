package com.xxcactussell.domain

data class GetCommands(
    val scope: BotCommandScope,
    val languageCode: String
) : Function
