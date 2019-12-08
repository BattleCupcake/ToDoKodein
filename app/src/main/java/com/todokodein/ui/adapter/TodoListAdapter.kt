package com.todokodein.ui.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import com.todokodein.data.db.entity.TodoItemEntity

class TodoListAdapter(
    activity: Activity,
    private var data: LiveData<out List<TodoItemEntity>>
) : BaseAdapter() {

    private val mInflater: LayoutInflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    private lateinit var callback: TodoListAdapterListener

    interface TodoListAdapterListener {
        fun upsertItem(item: TodoItemEntity)
        fun deleteItem(item: TodoItemEntity)
        fun editItem(item: TodoItemEntity)
    }

    fun setAdapterListener(mCallback: TodoListAdapterListener) {this.callback = mCallback}

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val holder: ViewHolder

        var view = convertView
        if (view == null) {
            view = mInflater.inflate()
        }
    }

    override fun getItem(position: Int): Any {
    }

    override fun getItemId(position: Int): Long {
    }

    override fun getCount(): Int {
    }

    private inner class ViewHolder{
        internal var checkbox: CheckBox? = null
        internal var tvTitle: TextView? = null
        internal var ivEdit: ImageView? = null
        internal var ivDelete: ImageView? = null

    }
}