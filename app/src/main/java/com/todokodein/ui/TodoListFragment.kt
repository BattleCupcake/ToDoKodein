package com.todokodein.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.todokodein.R
import com.todokodein.data.db.entity.TodoItemEntity
import com.todokodein.ui.adapter.TodoListAdapter
import com.todokodein.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.dialog.view.*
import kotlinx.android.synthetic.main.todo_list_fragment.*
import kotlinx.coroutines.launch
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(TodoViewModel::class.java)

        fetchData()
    }

    private fun fetchData() = launch {
        val todoList = viewModel.todoList.await()
        todoList.observe(this@TodoListFragment, Observer {
            if (it == null) return@Observer
            todoListData = todoList

            bindUI()
        })
    }

    private fun bindUI() {
        when (todoListData.value!!.isEmpty()) {
            true -> {
                tvPlaceHolder.visibility = View.GONE
            }
            else -> {
                val adapter = activity?.let { TodoListAdapter(it, todoListData) }
                adapter?.setAdapterListener(this)
                lvTodos.adapter = adapter
                tvPlaceHolder.visibility = View.GONE
                lvTodos.visibility = View.VISIBLE
            }
        }

        fabAdd.setOnClickListener {
            showDialog(null)
        }
    }

    private fun showDialog(item: TodoItemEntity?) {
        val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val dialogLayout = inflater.inflate(R.layout.dialog, null)
        dialogLayout.etTitle.setText(item?.title)


    }
}