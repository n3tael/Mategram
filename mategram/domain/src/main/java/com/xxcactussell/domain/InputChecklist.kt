package com.xxcactussell.domain

data class InputChecklist(
    val title: FormattedText,
    val tasks: List<InputChecklistTask>,
    val othersCanAddTasks: Boolean,
    val othersCanMarkTasksAsDone: Boolean
) : Object
