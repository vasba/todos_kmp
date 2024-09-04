package org.example.todos.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.example.todos.db.TodoItem
import org.example.todos.repo.TaskRepository
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class TodoViewModel(private val repository: TaskRepository) : ViewModel() {

    val tasks: MutableSharedFlow<List<TodoItem>> = MutableSharedFlow(1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    fun loadData() {
        viewModelScope.launch {
            val todoList = repository.loadTodos()
            tasks.tryEmit(todoList)
        }
    }

    fun deleteTodoItem(todoItem: TodoItem) {
        viewModelScope.launch {
            repository.deleteTodo(todoItem)
            loadData()
        }
    }

    fun markTodoAsComplete(id: Int, isCompleted: Boolean) {
        viewModelScope.launch {
            repository.markTodoAsComplete(id, isCompleted)
            loadData()
        }
    }

    fun updateTodoItem(todoItem: TodoItem) {
        viewModelScope.launch {
            repository.updateTodo(todoItem)
            loadData()
        }
    }
}