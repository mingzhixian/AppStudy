package edu.rui.app1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UsrAdapter(val usrList: List<Usr>) : RecyclerView.Adapter<UsrAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewPhone: TextView = view.findViewById(R.id.textViewPhone)
        val textViewPassword: TextView = view.findViewById(R.id.textViewPassword)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.usrs, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val usr = usrList[position]
        holder.textViewPhone.text=usr.Phone
        holder.textViewPassword.text = usr.Password
    }

    override fun getItemCount() = usrList.size

}