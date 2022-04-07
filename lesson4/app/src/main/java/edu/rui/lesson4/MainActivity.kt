package edu.rui.lesson4

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), View.OnClickListener {
    val todoItems = arrayListOf<TodoItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //获取数据
        initData()

        //生成界面
        val recycler = findViewById<RecyclerView>(R.id.menu)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = TodoAdapter(todoItems,this.findViewById<ConstraintLayout>(R.id.todo_detail))

        //设置监听
        this.findViewById<FloatingActionButton>(R.id.todo_add).setOnClickListener(this)
        this.findViewById<TextView>(R.id.todo_detail_cancel).setOnClickListener(this)
        this.findViewById<TextView>(R.id.todo_detail_ok).setOnClickListener(this)
    }

    private fun initData() {
        //数据库获取
        for (i in 0..4) {
            todoItems.add(TodoItem("第一条待办", 0))
            todoItems.add(TodoItem("第二条待办", 1))
            todoItems.add(TodoItem("第三条待办", 1))
            todoItems.add(TodoItem("第四条待办", 0))
        }
    }

    private fun deleteItem() {
        //删除数据
    }


    //展示输入框
    override fun onClick(view: View?) {
        var id: Int? = view?.id
        when (id) {
            R.id.todo_add -> {
                this.findViewById<ConstraintLayout>(R.id.todo_detail).visibility = VISIBLE
            }
            R.id.todo_detail_ok -> {
                this.findViewById<ConstraintLayout>(R.id.todo_detail).visibility = GONE
            }
            R.id.todo_detail_cancel -> {
                this.findViewById<ConstraintLayout>(R.id.todo_detail).visibility = GONE
            }
        }
    }
}