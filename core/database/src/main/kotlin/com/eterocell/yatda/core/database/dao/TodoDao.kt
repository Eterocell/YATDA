package com.eterocell.yatda.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.eterocell.yatda.core.database.model.TodoItemEntity
import kotlinx.coroutines.flow.Flow
import kotlin.uuid.Uuid

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: TodoItemEntity)

    @Update
    suspend fun update(item: TodoItemEntity)

    @Delete
    suspend fun delete(item: TodoItemEntity)

    @Query("SELECT * FROM todo_items ORDER BY relevantInstant ASC")
    suspend fun getAll(): List<TodoItemEntity>

    @Query("SELECT * FROM todo_items WHERE id = :id LIMIT 1")
    suspend fun getById(id: Uuid): TodoItemEntity?

    // ✅ Reactive versions
    @Query("SELECT * FROM todo_items ORDER BY relevantInstant ASC")
    fun observeAll(): Flow<List<TodoItemEntity>>

    @Query("SELECT * FROM todo_items WHERE isCompleted = 0 ORDER BY relevantInstant ASC")
    fun observeIncomplete(): Flow<List<TodoItemEntity>>

    @Query("SELECT * FROM todo_items WHERE isCompleted = 1 ORDER BY completedDateInstant DESC")
    fun observeCompleted(): Flow<List<TodoItemEntity>>

    @Query("SELECT * FROM todo_items WHERE isImportant = 1 ORDER BY relevantInstant ASC")
    fun observeImportant(): Flow<List<TodoItemEntity>>

    @Query("SELECT * FROM todo_items WHERE title LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%' ORDER BY relevantInstant ASC")
    fun search(query: String): Flow<List<TodoItemEntity>>

    @Query("DELETE FROM todo_items WHERE isCompleted = 1")
    suspend fun deleteAllCompleted()
}
