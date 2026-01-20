package com.xxcactussell.domain

data class MessageChecklistTasksAdded(
    val checklistMessageId: Long,
    val tasks: List<ChecklistTask>
) : MessageContent
