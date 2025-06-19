package com.eterocell.yatda.core.data.repository

import com.eterocell.yatda.core.database.dao.TodoDao
import com.eterocell.yatda.core.database.model.mapper.TodoItemMapper
import com.eterocell.yatda.core.model.TodoItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock
import javax.inject.Inject
import kotlin.uuid.Uuid

internal class DefaultTodoRepository
    @Inject
    constructor(
        private val todoDao: TodoDao,
        private val mapper: TodoItemMapper,
    ) : TodoRepository {
        override fun getAllTodos(): Flow<List<TodoItem>> =
            todoDao.observeAll().map { it.map(mapper::entityToModel) }

        override fun getIncompleteTodos(): Flow<List<TodoItem>> =
            todoDao.observeIncomplete().map { it.map(mapper::entityToModel) }

        override fun getCompletedTodos(): Flow<List<TodoItem>> =
            todoDao.observeCompleted().map { it.map(mapper::entityToModel) }

        override fun getImportantTodos(): Flow<List<TodoItem>> =
            todoDao.observeImportant().map { it.map(mapper::entityToModel) }

        override fun searchTodos(query: String): Flow<List<TodoItem>> =
            todoDao.search(query).map { it.map(mapper::entityToModel) }

        override suspend fun getTodoById(id: Uuid): TodoItem? =
            todoDao.getById(id)?.let(mapper::entityToModel)

        override suspend fun upsert(todo: TodoItem) {
            todoDao.insert(mapper.modelToEntity(todo))
        }

        override suspend fun update(todo: TodoItem) {
            todoDao.update(mapper.modelToEntity(todo))
        }

        override suspend fun delete(todo: TodoItem) {
            todoDao.delete(mapper.modelToEntity(todo))
        }

        override suspend fun deleteAllCompleted() {
            todoDao.deleteAllCompleted()
        }

        override suspend fun markAsCompleted(todo: TodoItem) {
            val updated =
                todo.copy(
                    isCompleted = true,
                    completedDateInstant = Clock.System.now(),
                    lastModifiedInstant = Clock.System.now(),
                )
            todoDao.update(mapper.modelToEntity(updated))
        }

        override suspend fun unmarkAsCompleted(todo: TodoItem) {
            val updated =
                todo.copy(
                    isCompleted = false,
                    completedDateInstant = null,
                    lastModifiedInstant = Clock.System.now(),
                )
            todoDao.update(mapper.modelToEntity(updated))
        }
    }
