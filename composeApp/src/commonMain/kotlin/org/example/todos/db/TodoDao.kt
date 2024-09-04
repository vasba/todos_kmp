package org.example.todos.db

import androidx.room.*

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo_items")
    suspend fun getAll(): List<TodoItem>

    @Insert
    suspend fun insert(todoItem: TodoItem)

    @Update
    suspend fun update(todoItem: TodoItem)

    @Delete
    suspend fun delete(todoItem: TodoItem)
}