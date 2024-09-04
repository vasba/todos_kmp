package org.example.todos

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.example.todos.app.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "TODOs",
    ) {
        App()
    }
}