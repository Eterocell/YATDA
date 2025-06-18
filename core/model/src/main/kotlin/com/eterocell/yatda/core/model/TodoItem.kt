package com.eterocell.yatda.core.model

import kotlinx.datetime.Instant
import kotlin.uuid.Uuid

data class TodoItem(
    val id: Uuid? = null,
    val creationInstant: Instant,
    val title: String,
    val description: String?,
    val isCompleted: Boolean,
    val completedDateInstant: Instant?,
    val lastModifiedInstant: Instant,
    val relevantInstant: Instant,
    val isImportant: Boolean,
)
