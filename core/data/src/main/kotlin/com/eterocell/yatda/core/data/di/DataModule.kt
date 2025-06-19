package com.eterocell.yatda.core.data.di

import com.eterocell.yatda.core.data.repository.DefaultTodoRepository
import com.eterocell.yatda.core.data.repository.TodoRepository
import com.eterocell.yatda.core.database.model.mapper.DefaultTodoItemMapper
import com.eterocell.yatda.core.database.model.mapper.TodoItemMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    internal abstract fun bindsTodoRepository(
        todoRepository: DefaultTodoRepository,
    ): TodoRepository

    @Binds
    @Singleton
    internal abstract fun bindsTodoItemMapper(todoItemMapper: DefaultTodoItemMapper): TodoItemMapper
}
