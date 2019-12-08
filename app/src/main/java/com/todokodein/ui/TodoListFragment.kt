package com.todokodein.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import com.todokodein.R
import com.todokodein.data.db.entity.TodoItemEntity
import com.todokodein.ui.adapter.TodoListAdapter
import com.todokodein.ui.base.ScopedFragment
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class TodoListFragment : ScopedFragment(), KodeinAware, TodoListAdapter.TodoListAdapterListener {

    override val kodein by closestKodein()

    private val viewModelFactory: TodoViewModelFactory by instance()
    private lateinit var viewModel: TodoViewModel
    private lateinit var todoListData: LiveData<out List<TodoItemEntity>>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.todo_list_fragment, container, false)
    }
}