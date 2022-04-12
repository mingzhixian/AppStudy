package edu.rui.lesson4

import android.annotation.SuppressLint
import android.content.ContentValues
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TodoAdapter(private val main: MainActivity) :
    RecyclerView.Adapter<TodoAdapter.ViewHolder>() {
    val getdetail: ConstraintLayout = main.findViewById(R.id.todo_detail)
    val getadd: FloatingActionButton = main.findViewById(R.id.todo_add)
    private var helper = DbHelper(this.main, "todo.db", 1)
    var todoItems = initData()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val content: TextView = view.findViewById(R.id.todo_item_content)
        val status: ImageView = view.findViewById(R.id.todo_item_status)
        val todoItem: ConstraintLayout = view.findViewById(R.id.todo_item)
        val done: ImageView = view.findViewById(R.id.todo_item_done)
        val delete: ImageView = view.findViewById(R.id.todo_item_delete)
        val todoDetail: ConstraintLayout = getdetail
        val todoAdd: FloatingActionButton = getadd
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)
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
        holder.done.setOnClickListener {
            done(position)
        }
        holder.delete.setOnClickListener {
            delete(position)
        }
        holder.todoItem.setOnClickListener {
            this.main.detailPosition = position
            holder.todoDetail.findViewById<EditText>(R.id.todo_item_edit)
                .setText(this.todoItems[position].content)
            holder.todoDetail.apply {
                alpha = 0f
                visibility = View.VISIBLE
                animate()
                    .alpha(1f)
                    .setDuration(360.toLong())
                    .setListener(null)
            }
            holder.todoAdd.visibility = View.GONE
        }
    }

    override fun getItemCount() = todoItems.size

    //初始化数据
    @SuppressLint("Range")
    private fun initData(): ArrayList<TodoItem> {
        val todoItems = ArrayList<TodoItem>()
        //数据库获取
        val cursor =
            helper.readableDatabase.query("ToDoDb", null, null, null, null, null, "id desc")
        if (cursor.moveToFirst()) {
            do {
                todoItems.add(
                    TodoItem(
                        cursor.getInt(cursor.getColumnIndex("id")),
                        cursor.getString(cursor.getColumnIndex("content")),
                        cursor.getInt(cursor.getColumnIndex("status"))
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        return todoItems
    }

    //输入框确认选项操作
    @SuppressLint("NotifyDataSetChanged")
    fun detailOk(position: Int, content: String) {
        if (position >= todoItems.size) {
            val values = ContentValues().apply {
                put("content", content)
                put("status", 0)
            }
            val id = helper.writableDatabase.insert("ToDoDb", null, values).toInt()
            todoItems.add(TodoItem(id, content, 0))
        } else {
            val values = ContentValues().apply {
                put("content", content)
            }
            helper.writableDatabase.update(
                "ToDoDb",
                values,
                "id = ?",
                arrayOf(todoItems[position].id.toString())
            )
            todoItems[position].content = content
        }
        notifyDataSetChanged()
    }

    //item侧滑选项操作
    @SuppressLint("NotifyDataSetChanged")
    private fun done(position: Int) {
        val values = ContentValues().apply {
            put("status", 1)
        }
        helper.writableDatabase.update(
            "ToDoDb",
            values,
            "id = ?",
            arrayOf(todoItems[position].id.toString())
        )
        todoItems[position].status = 1
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun delete(position: Int) {
        helper.writableDatabase.delete(
            "ToDoDb",
            "id = ?",
            arrayOf(todoItems[position].id.toString())
        )
        todoItems.removeAt(position)
        notifyItemRemoved(position)
        notifyDataSetChanged()
    }
}

