package com.eterocell.yatda.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.eterocell.yatda.core.database.dao.TodoDao
import com.eterocell.yatda.core.database.model.TodoItemEntity
import com.eterocell.yatda.core.database.util.InstantConverter
import com.eterocell.yatda.core.database.util.UuidConverter

@Database(
    entities = [TodoItemEntity::class],
    version = 1,
)
@TypeConverters(
    InstantConverter::class,
    UuidConverter::class,
)
internal abstract class YATDADatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}
