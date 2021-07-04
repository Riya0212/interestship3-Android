package com.wecure.doctor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_doctor_recycler_view.view.*

class DoctorRecyclerAdapter(private var name:List<String>,private var category:List<String>,private var profile:List<Int>):
    RecyclerView.Adapter<DoctorRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemname: TextView = itemView.findViewById(R.id.txtNameOfDoctor)!!
        val itemcategory: TextView = itemView.findViewById(R.id.txtcategoryOfDoctor)!!
        val itemprofile: ImageView = itemView.findViewById(R.id.imgdoctorphoto)!!


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
        holder.itemcategory.text=category[position]
        holder.itemprofile.setImageResource(profile[position])
    }


}