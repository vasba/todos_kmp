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

    @Query("UPDATE todo_items SET isCompleted = :isCompleted WHERE id = :id")
    suspend fun markAsComplete(id: Int, isCompleted: Boolean)

    @Query("UPDATE todo_items SET startDate = :startDate WHERE id = :id")
    suspend fun setStartDate(id: Int, startDate: String)

}