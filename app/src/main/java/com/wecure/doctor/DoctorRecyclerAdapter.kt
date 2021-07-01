package com.wecure.doctor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_doctor_recycler_view.view.*

class DoctorRecyclerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<DoctorModel> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DoctorListHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.activity_doctor_recycler_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {

            is DoctorListHolder -> {
                holder.bind(items.get(position))
            }

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
    fun submitList(blogList: List<DoctorModel>){
        items = blogList
    }




class DoctorListHolder
constructor(
    itemView: View
): RecyclerView.ViewHolder(itemView){

    val doc_image = itemView.imgdoctorphoto
    val doc_name = itemView.txtNameOfDoctor
    val doc_category = itemView.txtcategoryOfDoctor

    fun bind(doctormodel: DoctorModel){

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        Glide.with(itemView.context)
            .applyDefaultRequestOptions(requestOptions)
            .load(doctormodel.image)
            .into(doc_image)
        doc_name.setText(doctormodel.name)
        doc_category.setText(doctormodel.desc)

    }
}
}