package com.xxcactussell.domain

data class DeleteChatMessagesByDate(
    val chatId: Long,
    val minDate: Int,
    val maxDate: Int,
    val revoke: Boolean
) : Function
