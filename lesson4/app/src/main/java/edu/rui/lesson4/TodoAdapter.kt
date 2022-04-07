package edu.rui.lesson4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(val todoItems: List<TodoItem>,detail: ConstraintLayout) : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val content: TextView = view.findViewById(R.id.todo_item_content)
        val status: ImageView = view.findViewById(R.id.todo_item_status)
        val todoItem: LinearLayout = view.findViewById(R.id.todo_item)
        val todoDetail: ConstraintLayout =detail
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = todoItems[position]
        holder.content.text = item.content
        when (item.status) {
            2 -> holder.status.setImageResource(R.drawable.status_bad)
            1 -> holder.status.setImageResource(R.drawable.status_ok)
            0 -> holder.status.setImageResource(R.drawable.status_todo)
        }
        holder.todoItem.setOnClickListener {
            holder.todoDetail.visibility = View.VISIBLE
        }
    }

    override fun getItemCount() = todoItems.size
}