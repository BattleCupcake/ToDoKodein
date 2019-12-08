package com.todokodein.data.repository

import androidx.lifecycle.LiveData
import com.todokodein.data.db.dao.TodoItemDao
import com.todokodein.data.db.entity.TodoItemEntity
import kotlinx.coroutines.*

class TodoRepositoryImpl(
    private val todoListDao: TodoItemDao

) : TodoRepository {
    private val scope = CoroutineScope(Dispatchers.Default)

    override suspend fun getTodoList(): LiveData<out List<TodoItemEntity>> {
        return withContext(Dispatchers.IO) {todoListDao.getTodoItems()}
    }

    override fun upsertTodoItem(item: TodoItemEntity) = scope.launch{
        todoListDao.upsert(item)
    }

    override fun deleteTodoItem(item: TodoItemEntity) = scope.launch{
        todoListDao.delete(item)
    }

}
