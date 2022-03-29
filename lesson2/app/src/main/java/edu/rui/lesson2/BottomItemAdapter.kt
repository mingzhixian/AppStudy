package edu.rui.lesson2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class BottomItemAdapter(act: FragmentActivity) : FragmentStateAdapter(act) {
    private val category = arrayListOf("微信","通讯录","发现","我")
    override fun getItemCount(): Int {
        return category.size
    }

    override fun createFragment(position: Int): Fragment {
        val item = getItem(position)
        return BottomItemFragment.newInstance(item)
    }

    fun getItem(pos: Int): String {
        return category[pos]
    }

}