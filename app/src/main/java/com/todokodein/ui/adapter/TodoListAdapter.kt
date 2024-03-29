package com.todokodein.ui.adapter

import android.app.Activity
import android.content.Context
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.todokodein.R
import com.todokodein.data.db.entity.TodoItemEntity
import kotlinx.android.synthetic.main.list_item.view.*

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
            view = mInflater.inflate(R.layout.list_item, null)

            holder = ViewHolder()
            holder.checkbox = view.checkbox
            holder.tvTitle = view.tvTitle
            holder.ivEdit = view.ivEdit
            holder.ivDelete = view.ivDelete

            view.tag = holder
        } else {
            holder = view.tag as ViewHolder
        }

        val item = data.value?.get(position)

        holder.checkbox!!.isChecked = item!!.isChecked
        if (item.isChecked) {
            holder.tvTitle!!.paintFlags = holder.tvTitle!!.paintFlags or STRIKE_THRU_TEXT_FLAG
        }

        holder.checkbox!!.setOnCheckedChangeListener { _, isChecked ->
            item.isChecked = isChecked
            callback.upsertItem(item)
        }

        holder.tvTitle!!.text = item.title

        holder.ivEdit!!.setOnClickListener {
            callback.editItem(item)
        }
        holder.ivDelete!!.setOnClickListener {
            callback.deleteItem(item)
        }

        return view!!
    }

    override fun getItem(position: Int): Any {
        return data.value!![position]
    }

    override fun getItemId(position: Int): Long {
        return data.value!![position].id.toLong()
    }

    override fun getCount(): Int {
        return data.value!!.size
    }

    private inner class ViewHolder{
        internal var checkbox: CheckBox? = null
        internal var tvTitle: TextView? = null
        internal var ivEdit: ImageView? = null
        internal var ivDelete: ImageView? = null

    }
}