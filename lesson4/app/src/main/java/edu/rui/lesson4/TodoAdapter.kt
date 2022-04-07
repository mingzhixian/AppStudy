package edu.rui.lesson4

import android.renderscript.Int2
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TodoAdapter(val todoItems: List<TodoItem>, main: MainActivity) :
    RecyclerView.Adapter<TodoAdapter.ViewHolder>() {
    val getdetail = main.findViewById<ConstraintLayout>(R.id.todo_detail)
    val getadd=main.findViewById<FloatingActionButton>(R.id.todo_add)

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val content: TextView = view.findViewById(R.id.todo_item_content)
        val status: ImageView = view.findViewById(R.id.todo_item_status)
        val todoItem: LinearLayout = view.findViewById(R.id.todo_item)
        val todoDetail: ConstraintLayout = getdetail
        val todoAdd:FloatingActionButton=getadd
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
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
            holder.todoDetail.apply {
                alpha = 0f
                visibility = View.VISIBLE
                animate()
                    .alpha(1f)
                    .setDuration(360.toLong())
                    .setListener(null)
            }
            holder.todoAdd.visibility=View.GONE
        }
    }

    override fun getItemCount() = todoItems.size
}