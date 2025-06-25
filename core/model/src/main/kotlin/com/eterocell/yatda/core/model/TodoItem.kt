package com.eterocell.yatda.core.model

import kotlin.time.Clock
import kotlin.time.Instant
import kotlin.uuid.Uuid

data class TodoItem(
    val id: Uuid? = null,
    val creationInstant: Instant = Clock.System.now(),
    val title: String = "",
    val description: String? = null,
    val isCompleted: Boolean = false,
    val completedDateInstant: Instant? = null,
    val lastModifiedInstant: Instant = Clock.System.now(),
    val relevantInstant: Instant? = null,
    val isImportant: Boolean = false,
)
