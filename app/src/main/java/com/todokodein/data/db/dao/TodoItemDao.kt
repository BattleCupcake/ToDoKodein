package com.todokodein.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.todokodein.data.db.entity.TodoItemEntity

@Dao
interface TodoItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(todoItemEntity: TodoItemEntity)

    @Delete
    fun delete(todoItemEntity: TodoItemEntity)

    @Query("select * from todo_items order by isChecked ASC")
    fun getTodoItems(): LiveData<List<TodoItemEntity>>
}