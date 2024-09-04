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

    suspend fun deleteTodo(todoEntity: TodoItem) {
        dao.delete(todoEntity)
    }

    suspend fun markTodoAsComplete(id: Int, isCompleted: Boolean) {
        dao.markAsComplete(id, isCompleted)
    }

    suspend fun setTodoStartDate(id: Int, startDate: String) {
        dao.setStartDate(id, startDate)
    }

    suspend fun updateTodo(todoEntity: TodoItem) {
        dao.update(todoEntity)
    }
}