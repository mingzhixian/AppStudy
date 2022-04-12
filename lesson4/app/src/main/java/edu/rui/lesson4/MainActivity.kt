package edu.rui.lesson4

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginBottom
import androidx.core.view.marginTop
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity(), View.OnClickListener {
    var detailPosition = 0
    private var todoAdapter: TodoAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //生成界面
        todoAdapter=TodoAdapter(this)
        val recycler = findViewById<RecyclerView>(R.id.menu)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter =todoAdapter

        //设置监听
        this.findViewById<FloatingActionButton>(R.id.todo_add).setOnClickListener(this)
        this.findViewById<ConstraintLayout>(R.id.todo_detail).setOnClickListener(this)
        this.findViewById<TextView>(R.id.todo_detail_cancel).setOnClickListener(this)
        this.findViewById<TextView>(R.id.todo_detail_ok).setOnClickListener(this)
        titleOnScroll()
    }

    //吸顶效果
    private fun titleOnScroll() {
        val title = this.findViewById<TextView>(R.id.todo_title)
        val recyclerView = this.findViewById<RecyclerView>(R.id.menu)
        recyclerView.addOnScrollListener(object : OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    if (title.marginTop > 0) {
                        return
                    }
                    if (title.marginBottom > 0) {
                        return
                    }
                    if (title.textSize > 24) {
                        return
                    }
                }
            }
        })
    }

    //展示输入框
    override fun onClick(view: View?) {
        val id: Int? = view?.id
        val todoDetail = this.findViewById<ConstraintLayout>(R.id.todo_detail)
        val todoAdd = this.findViewById<FloatingActionButton>(R.id.todo_add)
        when (id) {
            R.id.todo_add -> {
                detailPosition= todoAdapter?.todoItems!!.size
                todoAdd.visibility = GONE
                todoDetail.apply {
                    alpha = 0f
                    visibility = VISIBLE
                    animate()
                        .alpha(1f)
                        .setDuration(360.toLong())
                        .setListener(null)
                }
            }
            R.id.todo_detail_ok -> {
                todoAdapter?.detailOk(
                    detailPosition,
                    todoDetail.findViewById<EditText>(R.id.todo_item_edit).text.toString()
                )
                (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(this@MainActivity.currentFocus!!.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
                todoDetail.apply {
                    animate()
                        .alpha(0f)
                        .setDuration(360.toLong())
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                todoDetail.visibility = GONE
                            }
                        })
                }
                todoAdd.visibility = VISIBLE
            }
            R.id.todo_detail_cancel -> {
                todoDetail.apply {
                    animate()
                        .alpha(0f)
                        .setDuration(360.toLong())
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                todoDetail.visibility = GONE
                            }
                        })
                }
                todoAdd.visibility = VISIBLE
            }
            R.id.todo_detail -> {
                //拦截点击事件
            }
        }
    }
}
