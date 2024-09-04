package org.example.todos.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.todos.viewModels.TodoViewModel
import org.example.todos.db.TodoItem
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

@Composable
fun TodoList(viewModel: TodoViewModel, modifier: Modifier = Modifier, onAdd: () -> Unit) {
    val tasks by viewModel.tasks.collectAsState(emptyList())

    LaunchedEffect(Unit) {
        viewModel.loadData()
    }

    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onAdd.invoke() },
                modifier = modifier
            ) {
                Icon(Icons.Filled.Add, "")
            }
        }
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text(
                text = "TODOs", fontSize = 22.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            TaskView(tasks = tasks, viewModel, modifier)
        }
    }
}

@Composable
fun TaskView(tasks: List<TodoItem>, viewModel: TodoViewModel, modifier: Modifier = Modifier) {
    var editingTodo by remember { mutableStateOf<TodoItem?>(null) }
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(tasks) { task ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { editingTodo = task },
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(task.title, fontSize = 18.sp)
                    Text(task.description, fontSize = 14.sp)
                }
                Row {
                    Checkbox(
                        checked = task.isCompleted,
                        onCheckedChange = { isChecked ->
                            viewModel.markTodoAsComplete(task.id, !task.isCompleted)
                        }
                    )
                    IconButton(onClick = { viewModel.deleteTodoItem(task) }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete")
                    }
                }
            }
        }
    }

    editingTodo?.let { todo ->
        EditTodoDialog(
            todoItem = todo,
            viewModel = viewModel,
            onDismiss = { editingTodo = null }
        )
    }
}


