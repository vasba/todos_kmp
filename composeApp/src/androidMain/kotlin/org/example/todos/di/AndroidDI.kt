package org.example.todos.di

import org.example.todos.db.TodoDatabase
import org.example.todos.db.getDatabase
import org.koin.dsl.module


actual fun platformModule() = module {
    single<TodoDatabase> { getDatabase(get()) }
}
