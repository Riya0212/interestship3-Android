package com.wecure.patient

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView

class ApoointmentReqRecyclerAdapter(private var name:List<String>, private var date:List<String>, private var profile:List<Int>):
    RecyclerView.Adapter<ApoointmentReqRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemname: TextView = itemView.findViewById(R.id.txtNameOfpatient_doctor)!!
        val itemdate: TextView = itemView.findViewById(R.id.txtdateOfappointment_doctor)!!
        val itemprofile: ImageView = itemView.findViewById(R.id.imgdoctorphoto_doctor)!!
        val itembtn:AppCompatButton =itemView.findViewById(R.id.btn_view_homescreen_doctor)!!



        init {
            itemView.setOnClickListener { v: View ->
                val position: Int = adapterPosition
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.activity_doctor_recycler_view, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return profile.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemname.text = name[position]
        holder.itemdate.text=date[position]
        holder.itemprofile.setImageResource(profile[position])
        val context=holder.itembtn.context
        holder.itembtn.setOnClickListener {
            val intent = Intent( context, Appointment::class.java)
            context.startActivity(intent)
        }

    }


}