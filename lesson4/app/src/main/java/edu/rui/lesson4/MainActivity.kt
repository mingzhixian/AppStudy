package edu.rui.lesson4

import android.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    val todoItems = arrayListOf<TodoItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //获取数据
        initData()

        //生成界面
        val recycler = findViewById<RecyclerView>(R.id.menu)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = TodoAdapter(todoItems)

        //设置监听
//        this.findViewById<LinearLayout>(R.id.todo_item).setOnClickListener { showEdit(this) }
//        this.findViewById<FloatingActionButton>(R.id.todo_add)
//            .setOnClickListener { showEdit(this) }
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
//    private fun showEdit(View v) {
//        val id: Int = v.getId()
//        when (id) {
//            R.id.todo_item ->
//                R.id.todo_add
//            ->
//        }
//    }

//    var editorCallback1: EditorCallback = object : EditorCallback() {
//        fun onCancel() {
//            Toast.makeText(this@MainActivity, "cancel", Toast.LENGTH_SHORT).show()
//        }
//
//        fun onSubmit(content: String?) {
//            tvShow.setText(content)
//        }
//
//        fun onAttached(rootView: ViewGroup?) {}
//    }
}