package com.xxcactussell.domain

data class MarkChecklistTasksAsDone(
    val chatId: Long,
    val messageId: Long,
    val markedAsDoneTaskIds: IntArray,
    val markedAsNotDoneTaskIds: IntArray
) : Function
