package com.eterocell.yatda.core.database.di

import android.content.Context
import androidx.room.Room
import com.eterocell.yatda.core.database.YATDADatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
    @Provides
    @Singleton
    fun providesYATDADatabase(
        @ApplicationContext context: Context,
    ): YATDADatabase = Room
        .databaseBuilder(
            context,
            YATDADatabase::class.java,
            "yatda-databse",
        ).build()
}
