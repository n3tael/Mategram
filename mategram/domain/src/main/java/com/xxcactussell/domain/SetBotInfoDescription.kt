package com.xxcactussell.domain

data class SetBotInfoDescription(
    val botUserId: Long,
    val languageCode: String,
    val description: String
) : Function
