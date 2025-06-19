package com.eterocell.yatda.core.data.repository

import com.eterocell.yatda.core.model.TodoItem
import kotlinx.coroutines.flow.Flow
import kotlin.uuid.Uuid

interface TodoRepository {
    fun getAllTodos(): Flow<List<TodoItem>>

    fun getIncompleteTodos(): Flow<List<TodoItem>>

    fun getCompletedTodos(): Flow<List<TodoItem>>

    fun getImportantTodos(): Flow<List<TodoItem>>

    fun searchTodos(query: String): Flow<List<TodoItem>>

    suspend fun getTodoById(id: Uuid): TodoItem?

    suspend fun upsert(todo: TodoItem)

    suspend fun update(todo: TodoItem)

    suspend fun delete(todo: TodoItem)

    suspend fun deleteAllCompleted()

    suspend fun markAsCompleted(todo: TodoItem)

    suspend fun unmarkAsCompleted(todo: TodoItem)
}
