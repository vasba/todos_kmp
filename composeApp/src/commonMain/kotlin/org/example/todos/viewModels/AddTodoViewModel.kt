package org.example.todos.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.example.todos.repo.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.example.todos.db.TodoItem

class AddTodoViewModel(
    private val taskRepository: TaskRepository
) : ViewModel() {

    val titleText: MutableStateFlow<String> = MutableStateFlow<String>("")
    fun onConfirm() {
        viewModelScope.launch {
            taskRepository.addTodo(TodoItem(title = titleText.value, description = "", date = ""))
        }
    }
}