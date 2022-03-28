package edu.rui.app1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SecendActivity : AppCompatActivity() {
    val usrs = arrayListOf<Usr>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secend)
        initData()
        val recycler = findViewById<RecyclerView>(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = UsrAdapter(usrs)
    }

    private fun initData() {
        for( i in 0..4) {
            usrs.add(Usr("15503237656","zxcvbnm"))
            usrs.add(Usr("15503237655","mnbvcxz"))
            usrs.add(Usr("15503237654","1234567890"))
            usrs.add(Usr("15503237653","0987654321"))
            usrs.add(Usr("15503237652","qwertyuiop"))
            usrs.add(Usr("15503237651","poiuytrewq"))
            usrs.add(Usr("15503237650","asdfghjkl"))
        }
    }
}