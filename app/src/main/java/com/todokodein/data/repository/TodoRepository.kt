package com.todokodein.data.repository

import androidx.lifecycle.LiveData
import com.todokodein.data.db.entity.TodoItemEntity
import kotlinx.coroutines.Job

interface TodoRepository {
    suspend fun getTodoList() : LiveData<out List<TodoItemEntity>>

    fun upsertTodoItem(item: TodoItemEntity): Job
    fun deleteTodoItem(item: TodoItemEntity): Job
}