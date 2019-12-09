package com.todokodein.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.todokodein.data.repository.TodoRepository

class TodoViewModelFactory(
    private val todoRepository: TodoRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TodoViewModel(todoRepository) as T
    }
}