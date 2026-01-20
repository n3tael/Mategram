package com.xxcactussell.domain

data class Checklist(
    val title: FormattedText,
    val tasks: List<ChecklistTask>,
    val othersCanAddTasks: Boolean,
    val canAddTasks: Boolean,
    val othersCanMarkTasksAsDone: Boolean,
    val canMarkTasksAsDone: Boolean
) : Object
