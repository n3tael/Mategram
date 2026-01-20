package com.xxcactussell.domain

data class SetBotName(
    val botUserId: Long,
    val languageCode: String,
    val name: String
) : Function
