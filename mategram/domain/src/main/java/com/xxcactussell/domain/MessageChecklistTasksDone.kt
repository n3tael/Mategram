package com.xxcactussell.domain

data class MessageChecklistTasksDone(
    val checklistMessageId: Long,
    val markedAsDoneTaskIds: IntArray,
    val markedAsNotDoneTaskIds: IntArray
) : MessageContent
