package com.xxcactussell.domain

data class SetBotInfoShortDescription(
    val botUserId: Long,
    val languageCode: String,
    val shortDescription: String
) : Function
