package com.wecure.doctor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextClock
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistoryRecyclerAdapter(private var namehistory:List<String>,private var categoryhistory:List<String>,private var profilehistory:List<Int>,private var datehistory:List<String>):
        RecyclerView.Adapter<HistoryRecyclerAdapter.ViewHolder>(){
            inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
                val itemnameHistory:TextView = itemView.findViewById(R.id.txtNameOfDoctor_history)
                val itemcategoryHistory:TextView=itemView.findViewById(R.id.txtcategoryOfDoctor_history)
                val itemprofileHistory:ImageView=itemView.findViewById(R.id.imgdoctorphoto_history)
                val itemdateHistory:TextView=itemView.findViewById(R.id.txtdate_history)
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.activity_history_recycler_view, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemnameHistory.text = namehistory[position]
        holder.itemcategoryHistory.text=categoryhistory[position]
        holder.itemprofileHistory.setImageResource(profilehistory[position])
        holder.itemdateHistory.text=datehistory[position]
    }

    override fun getItemCount(): Int {
        return  profilehistory.size
    }
}



