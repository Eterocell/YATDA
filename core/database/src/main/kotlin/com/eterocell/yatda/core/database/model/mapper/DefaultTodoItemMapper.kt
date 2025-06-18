package com.eterocell.yatda.core.database.model.mapper

import com.eterocell.yatda.core.database.model.TodoItemEntity
import com.eterocell.yatda.core.database.model.asEntity
import com.eterocell.yatda.core.database.model.asExternalModel
import com.eterocell.yatda.core.model.TodoItem
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.uuid.Uuid

@Singleton
class DefaultTodoItemMapper @Inject constructor() : TodoItemMapper {
    override fun entityToModel(entity: TodoItemEntity): TodoItem =
        entity.asExternalModel()

    override fun modelToEntity(model: TodoItem): TodoItemEntity {
        return if (model.id == null) {
            model.asEntity(id = Uuid.random())
        } else {
            model.asEntity()
        }
    }
}
