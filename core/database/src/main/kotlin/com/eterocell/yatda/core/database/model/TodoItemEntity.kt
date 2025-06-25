package com.eterocell.yatda.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.eterocell.yatda.core.model.TodoItem
import kotlin.time.Instant
import kotlin.uuid.Uuid

@Entity(tableName = "todo_items")
data class TodoItemEntity(
    @PrimaryKey val id: Uuid = Uuid.random(),
    val creationInstant: Instant,
    val title: String,
    val description: String?,
    val isCompleted: Boolean,
    val completedDateInstant: Instant?,
    val lastModifiedInstant: Instant,
    val relevantInstant: Instant?,
    val isImportant: Boolean,
)

fun TodoItemEntity.asExternalModel() = TodoItem(
    id = id,
    creationInstant = creationInstant,
    title = title,
    description = description,
    isCompleted = isCompleted,
    completedDateInstant = completedDateInstant,
    lastModifiedInstant = lastModifiedInstant,
    relevantInstant = relevantInstant,
    isImportant = isImportant,
)

fun TodoItem.asEntity(id: Uuid = Uuid.random()): TodoItemEntity = TodoItemEntity(
    id = this.id ?: id,
    creationInstant = creationInstant,
    title = title,
    description = description,
    isCompleted = isCompleted,
    completedDateInstant = completedDateInstant,
    lastModifiedInstant = lastModifiedInstant,
    relevantInstant = relevantInstant,
    isImportant = isImportant,
)
