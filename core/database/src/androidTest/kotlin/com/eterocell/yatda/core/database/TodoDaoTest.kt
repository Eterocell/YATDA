package com.eterocell.yatda.core.database

import com.eterocell.yatda.core.database.model.asEntity
import com.eterocell.yatda.core.database.model.asExternalModel
import com.eterocell.yatda.core.model.TodoItem
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

internal class TodoDaoTest : DatabaseTest() {

    @Test
    fun getTodos() = runTest {
        insertTodos()

        val savedTodos = todoDao.getAll().map { it.asExternalModel() }

        assertEquals(
            listOf("1", "2", "3"),
            savedTodos.map { it.title },
        )
    }

    private suspend fun insertTodos() {
        val todos = listOf(
            TodoItem(title = "1"),
            TodoItem(title = "2"),
            TodoItem(title = "3"),
        )
        todos.map { it.asEntity() }.forEach {
            todoDao.insert(it)
        }
    }
}
