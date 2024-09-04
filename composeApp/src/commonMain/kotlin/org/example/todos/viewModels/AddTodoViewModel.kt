package org.example.todos.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.example.todos.repo.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.example.todos.db.TodoItem


class AddTodoViewModel(
    private val taskRepository: TaskRepository
) : ViewModel() {

    val titleText: MutableStateFlow<String> = MutableStateFlow<String>("")
    val descriptionText = MutableStateFlow("")
    
    fun onConfirm() {
        viewModelScope.launch {
            val currentDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
            val formattedDate = currentDateTime.toString()
            
            taskRepository.addTodo(TodoItem(title = titleText.value, description = "", date = formattedDate, startDate=""))
        }
    }
}