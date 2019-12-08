package com.todokodein.ui

import androidx.lifecycle.ViewModel
import com.todokodein.data.db.entity.TodoItemEntity
import com.todokodein.data.repository.TodoRepository
import com.todokodein.internal.lazyDeffered

class TodoViewModel(
    private val todoRepository: TodoRepository
) : ViewModel() {

    val todoList by lazyDeffered {
        todoRepository.getTodoList()
    }

    fun upsertTodoItem(item: TodoItemEntity) {
        todoRepository.upsertTodoItem(item)
    }

    fun deleteTodoItem(item: TodoItemEntity) {
        todoRepository.deleteTodoItem(item)
    }
}