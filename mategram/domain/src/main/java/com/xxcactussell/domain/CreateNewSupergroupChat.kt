package com.xxcactussell.domain

data class CreateNewSupergroupChat(
    val title: String,
    val isForum: Boolean,
    val isChannel: Boolean,
    val description: String,
    val location: ChatLocation,
    val messageAutoDeleteTime: Int,
    val forImport: Boolean
) : Function
