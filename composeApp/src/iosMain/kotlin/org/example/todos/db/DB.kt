package org.example.todos.db

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.example.todos.db.instantiateImpl
import platform.Foundation.NSHomeDirectory

fun getDatabaseBuilder(): RoomDatabase.Builder<TodoDatabase> {
    val dbFilePath = NSHomeDirectory() + DATABASE_NAME
    return Room.databaseBuilder<TodoDatabase>(
        name = dbFilePath,
        factory = { TodoDatabase::class.instantiateImpl() }
    ).setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
}

fun getDatabase(): TodoDatabase {
    return getDatabaseBuilder().build()
}
