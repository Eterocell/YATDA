package com.eterocell.yatda.core.database.model.mapper

import com.eterocell.yatda.core.database.model.TodoItemEntity
import com.eterocell.yatda.core.model.TodoItem

interface TodoItemMapper {
    fun entityToModel(entity: TodoItemEntity): TodoItem

    fun modelToEntity(model: TodoItem): TodoItemEntity
}
