package com.xxcactussell.domain

data class ChecklistTask(
    val id: Int,
    val text: FormattedText,
    val completedByUserId: Long,
    val completionDate: Int
) : Object
