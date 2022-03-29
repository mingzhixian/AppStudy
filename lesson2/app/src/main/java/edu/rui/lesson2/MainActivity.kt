package edu.rui.lesson2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    lateinit var adapter: BottomItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        val viewpager = findViewById<ViewPager2>(R.id.view_pager)
        adapter = BottomItemAdapter(this)
        viewpager.adapter = adapter

        TabLayoutMediator(tabLayout,viewpager){ tab,pos->
            val item = adapter.getItem(pos)
            tab.text = item
        }.attach()
    }
}