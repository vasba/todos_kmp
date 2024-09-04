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
}