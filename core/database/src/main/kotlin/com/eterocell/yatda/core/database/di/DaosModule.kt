package com.eterocell.yatda.core.database.di

import com.eterocell.yatda.core.database.YATDADatabase
import com.eterocell.yatda.core.database.dao.TodoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {
    @Provides
    fun providesTodoDao(database: YATDADatabase): TodoDao = database.todoDao()
}
