package org.example.todos.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.todos.viewModels.AddTodoViewModel

@Composable
fun AddTodoView(
    viewModel: AddTodoViewModel,
    modifier: Modifier = Modifier,
    back: () -> Unit
) {
    val title by viewModel.titleText.collectAsState()
    val description by viewModel.descriptionText.collectAsState()

    Column(modifier = modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Add Todo", fontSize = 22.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        TextField(
            value = title,
            modifier = modifier.fillMaxWidth().padding(vertical = 8.dp),
            onValueChange = {
                viewModel.titleText.value = it
            },
            label = { Text("Title") })

        TextField(
            value = description,
            modifier = modifier.fillMaxWidth().padding(vertical = 8.dp),
            onValueChange = {
                viewModel.descriptionText.value = it
            },
            label = { Text("Description") }
        )

        Button(
            onClick = {
                viewModel.onConfirm()
                back.invoke()
            },
            modifier = Modifier.padding(vertical = 16.dp),
        ) {
            Text(text = "Add")
        }
    }
}