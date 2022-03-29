package edu.rui.lesson2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FragmentItemAdapter(val recyclerItem: List<RecyclerItem>):
    RecyclerView.Adapter<FragmentItemAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val Text1: TextView = view.findViewById(R.id.recyclerText1)
        val Text2: TextView = view.findViewById(R.id.recyclerText2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_item, null, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val usr = recyclerItem[position]
        holder.Text1.text=usr.Text1
        holder.Text2.text= usr.Text2
    }

    override fun getItemCount() = recyclerItem.size
}