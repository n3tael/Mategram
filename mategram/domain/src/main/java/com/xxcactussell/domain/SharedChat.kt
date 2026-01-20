package com.xxcactussell.domain

data class SharedChat(
    val chatId: Long,
    val title: String,
    val username: String,
    val photo: Photo? = null
) : Object
