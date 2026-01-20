package com.xxcactussell.domain

data class AddChecklistTasks(
    val chatId: Long,
    val messageId: Long,
    val tasks: List<InputChecklistTask>
) : Function
