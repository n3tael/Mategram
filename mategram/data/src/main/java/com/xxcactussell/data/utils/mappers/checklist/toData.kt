package com.xxcactussell.data.utils.mappers.checklist

import com.xxcactussell.data.utils.mappers.formatted.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun Checklist.toData(): TdApi.Checklist = TdApi.Checklist(
    this.title.toData(),
    this.tasks.map { it.toData() }.toTypedArray(),
    this.othersCanAddTasks,
    this.canAddTasks,
    this.othersCanMarkTasksAsDone,
    this.canMarkTasksAsDone
)

fun ChecklistTask.toData(): TdApi.ChecklistTask = TdApi.ChecklistTask(
    this.id,
    this.text.toData(),
    this.completedByUserId,
    this.completionDate
)

