package com.xxcactussell.domain

data class GetBotName(
    val botUserId: Long,
    val languageCode: String
) : Function
