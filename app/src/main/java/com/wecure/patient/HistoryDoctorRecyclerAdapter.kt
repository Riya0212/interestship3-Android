package com.wecure.patient

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView

class HistoryDoctorRecyclerAdapter(private var namehistory:List<String>, private var reasonhistory:List<String>, private var profilehistory:List<Int>, private var datehistory:List<String>):
        RecyclerView.Adapter<HistoryDoctorRecyclerAdapter.ViewHolder>(){
            inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
                val itemnameHistory:TextView = itemView.findViewById(R.id.txtNameOfDoctor_history_doctor)!!
                val itemreasonHistory:TextView=itemView.findViewById(R.id.txtreason_history_doctor)!!
                val itemprofileHistory:ImageView=itemView.findViewById(R.id.imgdoctorphoto_history_doctor)!!
                val itemdateHistory:TextView=itemView.findViewById(R.id.txtdate_history_doctor)!!
                val itemPartivular: LinearLayout =itemView.findViewById(R.id.container_history_linear)!!

            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.activity_history_recycler_view_doctor, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemnameHistory.text = namehistory[position]
        holder.itemreasonHistory.text=reasonhistory[position]
        holder.itemprofileHistory.setImageResource(profilehistory[position])
        holder.itemdateHistory.text=datehistory[position]
        val context=holder.itemPartivular.context
        holder.itemPartivular.setOnClickListener {
            val intent = Intent( context, AppointmentDetail::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return  namehistory.size
    }
}



