package com.xxcactussell.domain

data class UpdateMessageFactCheck(
    val chatId: Long,
    val messageId: Long,
    val factCheck: FactCheck
) : Update
