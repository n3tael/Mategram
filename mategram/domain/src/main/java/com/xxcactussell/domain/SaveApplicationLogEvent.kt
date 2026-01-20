package com.xxcactussell.domain

data class SaveApplicationLogEvent(
    val type: String,
    val chatId: Long,
    val data: JsonValue
) : Function
