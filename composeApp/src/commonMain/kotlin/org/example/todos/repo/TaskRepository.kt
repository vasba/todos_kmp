package org.example.todos.repo

import org.example.todos.db.TodoDatabase
import org.example.todos.db.TodoDao
import org.example.todos.db.TodoItem

class TaskRepository(private val database: TodoDatabase) {
private val dao: TodoDao by lazy {
    database.todoDao()
}

    suspend fun addTodo(todoEntity: TodoItem) {
        dao.insert(todoEntity)
    }

    suspend fun loadTodos(): List<TodoItem> {
        return dao.getAll()
    }
}