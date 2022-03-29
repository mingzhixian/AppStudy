package edu.rui.lesson2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BottomItemFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private val recyclerItem = ArrayList<RecyclerItem>()
    private var title: String? = null

    private fun initData() {
        recyclerItem.add(RecyclerItem("text1-1", "text1-2"))
        recyclerItem.add(RecyclerItem("text2-1", "text2-2"))
        recyclerItem.add(RecyclerItem("text3-1", "text3-2"))
        recyclerItem.add(RecyclerItem("text4-1", "text4-2"))
        recyclerItem.add(RecyclerItem("text5-1", "text5-2"))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragments, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.frament_text).text = title
        initData()
        val recycler = view.findViewById<RecyclerView>(R.id.recyclerView)
        recycler.layoutManager = LinearLayoutManager(view.context)
        recycler.adapter = FragmentItemAdapter(recyclerItem)
    }

    companion object {
        @JvmStatic
        fun newInstance(str: String) =
            BottomItemFragment().apply {
                title = str
            }
    }
}