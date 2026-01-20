package com.xxcactussell.data.utils.mappers.checklist

import com.xxcactussell.data.utils.mappers.formatted.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.Checklist.toDomain(): Checklist = Checklist(
    title = this.title.toDomain(),
    tasks = this.tasks.map { it.toDomain() },
    othersCanAddTasks = this.othersCanAddTasks,
    canAddTasks = this.canAddTasks,
    othersCanMarkTasksAsDone = this.othersCanMarkTasksAsDone,
    canMarkTasksAsDone = this.canMarkTasksAsDone
)

fun TdApi.ChecklistTask.toDomain(): ChecklistTask = ChecklistTask(
    id = this.id,
    text = this.text.toDomain(),
    completedByUserId = this.completedByUserId,
    completionDate = this.completionDate
)

