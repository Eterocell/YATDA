package com.eterocell.yatda.core.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.eterocell.yatda.core.database.dao.TodoDao
import org.junit.After
import org.junit.Before

internal abstract class DatabaseTest {
    private lateinit var db: YATDADatabase
    protected lateinit var todoDao: TodoDao

    @Before
    fun setup() {
        db =
            run {
                val context = ApplicationProvider.getApplicationContext<Context>()
                Room
                    .inMemoryDatabaseBuilder(
                        context,
                        YATDADatabase::class.java,
                    ).build()
            }
        todoDao = db.todoDao()
    }

    @After
    fun teardown() {
        db.close()
    }
}
