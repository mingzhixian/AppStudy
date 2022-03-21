package edu.rui.app1

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    val TAG = "@@MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d(TAG, "onDestroy")
    }

    fun onClick(view: View) {
        Log.i(TAG, "onClick $(view.id)")
        //下一页
        val intent = Intent(this, SecendActivity::class.java)
        //加载动画
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = View.VISIBLE
        //注册与登陆
        when (view.id) {
            R.id.register -> {
                AlertDialog.Builder(this).apply {
                    setTitle("确认")
                    setMessage("请确认帐号密码是否输入正确。")
                    setCancelable(false)
                    setPositiveButton("OK") { dialog, which ->
                        progressBar.visibility = View.GONE
                        startActivity(intent)
                    }
                    show()
                }
            }
            R.id.login -> {
                progressBar.visibility = View.GONE
                startActivity(intent)
            }
        }
    }
}